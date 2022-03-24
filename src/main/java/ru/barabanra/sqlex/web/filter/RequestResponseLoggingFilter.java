package ru.barabanra.sqlex.web.filter;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.AbstractRequestLoggingFilter;
import org.springframework.web.util.ContentCachingRequestWrapper;
import org.springframework.web.util.ContentCachingResponseWrapper;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import static org.apache.commons.lang3.CharEncoding.UTF_8;
import static org.springframework.http.HttpHeaders.CONTENT_TYPE;
import static org.springframework.http.HttpHeaders.USER_AGENT;

@Slf4j
public class RequestResponseLoggingFilter extends AbstractRequestLoggingFilter {

    private static final String JSON_EMPTY_BODY = "{\"body\":\"empty-body\"}";

    public RequestResponseLoggingFilter(Integer maxPayload) {
        this.setIncludePayload(true);
        this.setMaxPayloadLength(maxPayload);
    }

    @Override
    protected void beforeRequest(HttpServletRequest request, String message) {
        request.setAttribute("INCOMING_REQUEST_TIME", LocalDateTime.now());
    }

    @Override
    protected void afterRequest(HttpServletRequest request, String message) {
    }

    private JSONObject buildMetaJsonForLog(Map<String, String> requestParams, HttpServletRequest request) {
        LocalDateTime requestTime = (LocalDateTime) request.getAttribute("INCOMING_REQUEST_TIME");
        Duration duration = Duration.between(requestTime, LocalDateTime.now());
        JSONObject metaInfo = new JSONObject();
        requestParams.forEach(metaInfo::put);
        metaInfo.put("duration", duration.toMillis());
        return metaInfo;
    }

    private String parseJsonToString(JSONObject jsonObject, Map<String, String> requestParams, HttpServletRequest request) {
        JSONObject requestWrapper = new JSONObject();
        requestWrapper.put("metaInfo", jsonObject);
        String payload = requestParams.getOrDefault("payload", "{}");
        requestWrapper.put("request", payload);
        return requestWrapper.toString();
    }

    private void logResponse(ContentCachingRequestWrapper requestWrapper, ContentCachingResponseWrapper responseWrapper) throws IOException {
        try {
            String message = createMessage(requestWrapper, "", "");
            Map<String, String> requestParams = new HashMap<>();
            String[] params = message.split(";~");
            parseRequest(params, requestParams, requestWrapper);
            JSONObject loggingJson = buildMetaJsonForLog(requestParams, requestWrapper);
            String responseBody =
                    Optional.ofNullable(IOUtils.toString(responseWrapper.getContentInputStream(), UTF_8)).orElse("");
            if (responseBody.isEmpty()) {
                responseBody = JSON_EMPTY_BODY;
            }
            loggingJson.put("responseBody", responseBody.replaceAll("\\\\", ""));
            loggingJson.put("httpStatus", responseWrapper.getStatusCode());
            String requestToLog = parseJsonToString(loggingJson, requestParams, requestWrapper);
            requestToLog = requestToLog.replaceAll("\\\\r\\\\n", "").replaceAll("\\\\", "");
            log.debug("{} ", requestToLog);
        } catch (Exception ex) {
            log.error("Unable to build response message", ex);
        } finally {
            responseWrapper.copyBodyToResponse();
        }
    }

    private void parseRequest(String[] params, Map<String, String> requestParams,
                              ContentCachingRequestWrapper request) {
        for (String param : params) {
            String[] keyValuePair = param.split("=", 2);
            requestParams.put(keyValuePair[0], keyValuePair[1]);
        }
        requestParams.put(CONTENT_TYPE, request.getHeader(CONTENT_TYPE));
        requestParams.put(USER_AGENT, request.getHeader(USER_AGENT));
        requestParams.put("http-method", request.getMethod());
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        ContentCachingRequestWrapper cachingRequestWrapper = new ContentCachingRequestWrapper(request, getMaxPayloadLength());
        ContentCachingResponseWrapper cachingResponseWrapper = new ContentCachingResponseWrapper(response);

        if (request.getContentLengthLong() > this.getMaxPayloadLength()) {
            log.info("Content-Length for {} is {} Can't log payload", request.getRequestURI(), request.getContentLengthLong());
        }
        super.doFilterInternal(cachingRequestWrapper, cachingResponseWrapper, filterChain);
        logResponse(cachingRequestWrapper, cachingResponseWrapper);
    }

    @Override
    protected String createMessage(HttpServletRequest request, String prefix, String suffix) {
        StringBuilder msg = new StringBuilder();
        msg.append(prefix);
        msg.append("uri=").append(request.getRequestURI());

        if (isIncludeQueryString()) {
            String queryString = request.getQueryString();
            if (queryString != null) {
                msg.append('?').append(queryString);
            }
        }
        if (isIncludeClientInfo()) {
            String client = request.getRemoteAddr();
            if (StringUtils.hasLength(client)) {
                msg.append(";~").append("client=").append(client);
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                msg.append(";~").append("session=").append(session.getId());
            }
            String user = request.getRemoteUser();
            if (user != null) {
                msg.append(";~").append("user=").append(user);
            }
        }
        if (isIncludeHeaders()) {
            msg.append(";~").append("headers=").append(new ServletServerHttpRequest(request).getHeaders());
        }
        if (isIncludePayload()) {
            String payload = getMessagePayload(request);
            if (payload != null) {
                msg.append(";~").append("payload=").append(payload);
            }
        }
        msg.append(suffix);
        return msg.toString();
    }

}