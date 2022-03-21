package com.assessment.http.requests.service;

import com.assessment.http.requests.model.HttpLogLine;
import com.assessment.http.requests.model.dtos.HostSitesStats;
import com.assessment.http.requests.model.dtos.HostStats;
import com.assessment.http.requests.model.dtos.RequestsPercentage;
import com.assessment.http.requests.model.dtos.SiteStats;
import com.assessment.http.requests.util.ValueComparator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

import static com.assessment.http.requests.util.Constants.REQUESTS_PARAM_TYPE_SUCCESSFUL;
import static com.assessment.http.requests.util.Constants.REQUESTS_PARAM_TYPE_UNSUCCESSFUL;

@Service
public class StatsGenerationService {

    @Autowired
    HttpLogSchedulerService httpLogSchedulerService;

    public List<SiteStats> getTopSites(int numberOfSites) {
        Map<String, Integer> sitesMap = new HashMap<>();
        for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
            String endpoint = httpLogLine.getEndpoint();
            if (endpoint == null) {
                continue;
            }
            if (sitesMap.get(endpoint) == null) {
                sitesMap.put(endpoint, 0);
            } else {
                sitesMap.put(endpoint, sitesMap.get(endpoint) + 1);
            }
        }
        ValueComparator bvc = new ValueComparator(sitesMap);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(bvc);
        sortedMap.putAll(sitesMap);
        int count = 0;
        List<SiteStats> topSites = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (count >= numberOfSites) break;
            topSites.add(new SiteStats(entry.getKey(), entry.getValue()));
            count++;
        }
        return topSites;
    }

    public RequestsPercentage getPercentageOfRequests(String type) {
        int allRequests = 0;
        int successfulRequests = 0;
        int unsuccessfulRequests = 0;
        for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
            String statusCode = httpLogLine.getStatusCode();
            if (statusCode == null) {
                continue;
            }
            allRequests++;
            int statusCodeInteger;
            try {
                statusCodeInteger = Integer.parseInt(httpLogLine.getStatusCode());
            } catch (Exception e) {
                continue;
            }
            if (statusCodeInteger >= 200 && statusCodeInteger < 400) {
                successfulRequests++;
            } else {
                unsuccessfulRequests++;
            }
        }
        if (REQUESTS_PARAM_TYPE_SUCCESSFUL.equals(type)) {
            return new RequestsPercentage(String.format("%.02f", ((double) successfulRequests / allRequests) * 100));
        }
        if (REQUESTS_PARAM_TYPE_UNSUCCESSFUL.equals(type)) {
            return new RequestsPercentage(String.format("%.02f", ((double) unsuccessfulRequests / allRequests) * 100));
        }
        return null;
    }

    public List<HostStats> getTopHosts(int numberOfHosts) {
        Map<String, Integer> hostsMap = new HashMap<>();
        for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
            String host = httpLogLine.getHost();
            if (host == null) {
                continue;
            }
            if (hostsMap.get(host) == null) {
                hostsMap.put(host, 0);
            } else {
                hostsMap.put(host, hostsMap.get(host) + 1);
            }
        }
        ValueComparator bvc = new ValueComparator(hostsMap);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(bvc);
        sortedMap.putAll(hostsMap);
        int count = 0;
        List<HostStats> topHosts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (count >= numberOfHosts) break;
            topHosts.add(new HostStats(entry.getKey(), entry.getValue()));
            count++;
        }
        return topHosts;
    }

    public List<HostSitesStats> getTopSitesTopHosts(int numberOfHosts, int numberOfSites) {
        Map<String, Integer> hostsMap = new HashMap<>();
        for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
            String host = httpLogLine.getHost();
            if (host == null) {
                continue;
            }
            if (hostsMap.get(host) == null) {
                hostsMap.put(host, 0);
            } else {
                hostsMap.put(host, hostsMap.get(host) + 1);
            }
        }
        ValueComparator bvcHosts = new ValueComparator(hostsMap);
        TreeMap<String, Integer> sortedMapHosts = new TreeMap<>(bvcHosts);
        sortedMapHosts.putAll(hostsMap);
        int count = 0;
        List<HostSitesStats> topHostSites = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMapHosts.entrySet()) {
            if (count >= numberOfHosts) break;
            topHostSites.add(new HostSitesStats(entry.getKey(), entry.getValue()));
            count++;
        }
        for (HostSitesStats topHostSite : topHostSites) {
            Map<String, Integer> sitesMap = new HashMap<>();
            for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
                if (topHostSite.getHost().equals(httpLogLine.getHost())) {
                    String endpoint = httpLogLine.getEndpoint();
                    if (endpoint == null) {
                        continue;
                    }
                    if (sitesMap.get(endpoint) == null) {
                        sitesMap.put(endpoint, 0);
                    } else {
                        sitesMap.put(endpoint, sitesMap.get(endpoint) + 1);
                    }
                }
            }
            ValueComparator bvcSites = new ValueComparator(sitesMap);
            TreeMap<String, Integer> sortedMapSites = new TreeMap<>(bvcSites);
            sortedMapSites.putAll(sitesMap);
            int countSites = 0;
            List<SiteStats> topSites = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : sortedMapSites.entrySet()) {
                if (countSites >= numberOfSites) break;
                topSites.add(new SiteStats(entry.getKey(), entry.getValue()));
                countSites++;
            }
            topHostSite.setTopSites(topSites);
        }
        return topHostSites;
    }
}
