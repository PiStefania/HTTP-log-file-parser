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

    private final HttpLogSchedulerService httpLogSchedulerService;

    @Autowired
    public StatsGenerationService(HttpLogSchedulerService httpLogSchedulerService) {
        this.httpLogSchedulerService = httpLogSchedulerService;
    }

    public List<SiteStats> getTopSites(int numberOfSites) {
        Map<String, Integer> sitesMap = new HashMap<>();
        for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
            String endpoint = httpLogLine.getEndpoint();
            sitesMap.merge(endpoint, 1, Integer::sum);
        }
        TreeMap<String, Integer> sortedMap = sortMap(sitesMap);
        int countSites = 0;
        List<SiteStats> topSites = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (countSites >= numberOfSites) {
                break;
            }
            topSites.add(new SiteStats(entry.getKey(), entry.getValue()));
            countSites++;
        }
        return topSites;
    }

    public RequestsPercentage getPercentageOfRequests(String type) {
        int allRequests = 0;
        int successfulRequests = 0;
        int unsuccessfulRequests = 0;
        for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
            int statusCodeInteger;
            try {
                statusCodeInteger = Integer.parseInt(httpLogLine.getStatusCode());
            } catch (Exception e) {
                continue;
            }
            allRequests++;
            if (statusCodeInteger >= 200 && statusCodeInteger < 400) {
                successfulRequests++;
            } else {
                unsuccessfulRequests++;
            }
        }
        if (allRequests == 0) {
            return null;
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
            hostsMap.merge(host, 1, Integer::sum);
        }
        TreeMap<String, Integer> sortedMap = sortMap(hostsMap);
        int countHosts = 0;
        List<HostStats> topHosts = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            if (countHosts >= numberOfHosts) {
                break;
            }
            topHosts.add(new HostStats(entry.getKey(), entry.getValue()));
            countHosts++;
        }
        return topHosts;
    }

    public List<HostSitesStats> getTopSitesTopHosts(int numberOfHosts, int numberOfSites) {
        Map<String, Integer> hostsMap = new HashMap<>();
        for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
            String host = httpLogLine.getHost();
            hostsMap.merge(host, 1, Integer::sum);
        }
        TreeMap<String, Integer> sortedMapHosts = sortMap(hostsMap);
        int countHosts = 0;
        List<HostSitesStats> topHostSites = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sortedMapHosts.entrySet()) {
            if (countHosts >= numberOfHosts) {
                break;
            }
            topHostSites.add(new HostSitesStats(entry.getKey(), entry.getValue()));
            countHosts++;
        }
        for (HostSitesStats topHostSite : topHostSites) {
            Map<String, Integer> sitesMap = new HashMap<>();
            for (HttpLogLine httpLogLine : httpLogSchedulerService.getHttpLogLines()) {
                if (topHostSite.getHost().equals(httpLogLine.getHost())) {
                    String endpoint = httpLogLine.getEndpoint();
                    sitesMap.merge(endpoint, 1, Integer::sum);
                }
            }
            TreeMap<String, Integer> sortedMapSites = sortMap(sitesMap);
            int countSites = 0;
            List<SiteStats> topSites = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : sortedMapSites.entrySet()) {
                if (countSites >= numberOfSites) {
                    break;
                }
                topSites.add(new SiteStats(entry.getKey(), entry.getValue()));
                countSites++;
            }
            topHostSite.setTopSites(topSites);
        }
        return topHostSites;
    }

    private TreeMap<String, Integer> sortMap(Map<String, Integer> map) {
        ValueComparator valueComparator = new ValueComparator(map);
        TreeMap<String, Integer> sortedMap = new TreeMap<>(valueComparator);
        sortedMap.putAll(map);
        return sortedMap;
    }
}
