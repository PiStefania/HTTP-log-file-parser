package com.assessment.http.requests.service;

import com.assessment.http.requests.model.HttpLogLine;
import com.assessment.http.requests.model.response.SuccessfullRequests;
import com.assessment.http.requests.model.response.TopHost;
import com.assessment.http.requests.model.response.TopHostTopSite;
import com.assessment.http.requests.model.response.TopSite;
import com.assessment.http.requests.util.HttpFileUtil;
import com.assessment.http.requests.util.ValueComparator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

import static com.assessment.http.requests.util.HttpFileUtil.findHttpLogLineParts;

@Component
public class StatsGenerationService {

    private static final Logger logger = LoggerFactory.getLogger(HttpFileUtil.class);
    private static final String logFilePath = "httpLogFiles/new_final_final_01.log";

    public List<TopSite> getTopSites() {
        Map<String, Integer> topSites = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new ClassPathResource(logFilePath).getFile()));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                HttpLogLine httpLogLine = findHttpLogLineParts(line);
                if (httpLogLine == null || httpLogLine.getEndpoint() == null) {
                    continue;
                }
                if (topSites.get(httpLogLine.getEndpoint()) == null) {
                    topSites.put(httpLogLine.getEndpoint(), 0);
                } else {
                    topSites.put(httpLogLine.getEndpoint(), topSites.get(httpLogLine.getEndpoint()) + 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            logger.error("Could not read file with title: {}", logFilePath, e);
            return null;
        }
        ValueComparator bvc = new ValueComparator(topSites);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        sorted_map.putAll(topSites);
        int count = 0;
        List<TopSite> topSitesList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted_map.entrySet()) {
            if (count >= 10) break;
            topSitesList.add(new TopSite(entry.getKey(), entry.getValue()));
            count++;
        }
        return topSitesList;
    }

    public SuccessfullRequests getSuccessfullRequests() {
        BufferedReader reader;
        int allRequests = 0;
        int successfulRequests = 0;
        try {
            reader = new BufferedReader(new FileReader(new ClassPathResource(logFilePath).getFile()));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                HttpLogLine httpLogLine = findHttpLogLineParts(line);
                if (httpLogLine == null) {
                    continue;
                }
                allRequests++;
                if (httpLogLine.getStatusCode() != null && (Integer.valueOf(httpLogLine.getStatusCode()) >= 200 && Integer.valueOf(httpLogLine.getStatusCode()) < 400)) {
                    successfulRequests++;
                }
            }
            reader.close();
        } catch (IOException e) {
            logger.error("Could not read file with title: {}", logFilePath, e);
            return null;
        }
        return new SuccessfullRequests(String.format("%.02f", ((double) successfulRequests / allRequests) * 100));
    }

    public SuccessfullRequests getUnsuccessfulRequests() {
        BufferedReader reader;
        int allRequests = 0;
        int unsuccessfulRequests = 0;
        try {
            reader = new BufferedReader(new FileReader(new ClassPathResource(logFilePath).getFile()));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                HttpLogLine httpLogLine = findHttpLogLineParts(line);
                if (httpLogLine == null) {
                    continue;
                }
                allRequests++;
                if (httpLogLine.getStatusCode() != null && (Integer.valueOf(httpLogLine.getStatusCode()) < 200 || Integer.valueOf(httpLogLine.getStatusCode()) >= 400)) {
                    unsuccessfulRequests++;
                }
            }
            reader.close();
        } catch (IOException e) {
            logger.error("Could not read file with title: {}", logFilePath, e);
            return null;
        }
        return new SuccessfullRequests(String.format("%.02f", ((double) unsuccessfulRequests / allRequests) * 100));
    }

    public List<TopHost> getTopHosts() {
        Map<String, Integer> topHosts = new HashMap<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new ClassPathResource(logFilePath).getFile()));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                HttpLogLine httpLogLine = findHttpLogLineParts(line);
                if (httpLogLine == null || httpLogLine.getHost() == null) {
                    continue;
                }
                if (topHosts.get(httpLogLine.getHost()) == null) {
                    topHosts.put(httpLogLine.getHost(), 0);
                } else {
                    topHosts.put(httpLogLine.getHost(), topHosts.get(httpLogLine.getHost()) + 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            logger.error("Could not read file with title: {}", logFilePath, e);
            return null;
        }
        ValueComparator bvc = new ValueComparator(topHosts);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        sorted_map.putAll(topHosts);
        int count = 0;
        List<TopHost> topHostList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted_map.entrySet()) {
            if (count >= 10) break;
            topHostList.add(new TopHost(entry.getKey(), entry.getValue()));
            count++;
        }
        return topHostList;
    }

    public List<TopHostTopSite> getTopSitesTopHosts() {
        Map<String, Integer> topHosts = new HashMap<>();
        List<HttpLogLine> httpLogLines = new ArrayList<>();
        BufferedReader reader;
        try {
            reader = new BufferedReader(new FileReader(new ClassPathResource(logFilePath).getFile()));
            String line = reader.readLine();
            while (line != null) {
                line = reader.readLine();
                HttpLogLine httpLogLine = findHttpLogLineParts(line);
                if (httpLogLine == null || httpLogLine.getHost() == null) {
                    continue;
                }
                httpLogLines.add(httpLogLine);
                if (topHosts.get(httpLogLine.getHost()) == null) {
                    topHosts.put(httpLogLine.getHost(), 0);
                } else {
                    topHosts.put(httpLogLine.getHost(), topHosts.get(httpLogLine.getHost()) + 1);
                }
            }
            reader.close();
        } catch (IOException e) {
            logger.error("Could not read file with title: {}", logFilePath, e);
            return null;
        }
        ValueComparator bvc = new ValueComparator(topHosts);
        TreeMap<String, Integer> sorted_map = new TreeMap<String, Integer>(bvc);
        sorted_map.putAll(topHosts);
        int count = 0;
        List<TopHostTopSite> topHostList = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : sorted_map.entrySet()) {
            if (count >= 10) break;
            topHostList.add(new TopHostTopSite(entry.getKey(), entry.getValue()));
            count++;
        }
        for(TopHostTopSite topHost: topHostList) {
            Map<String, Integer> topSites = new HashMap<>();
            for(HttpLogLine httpLogLine: httpLogLines) {
                if(topHost.getHost().equals(httpLogLine.getHost())) {
                    if (httpLogLine.getEndpoint() == null) {
                        continue;
                    }
                    if (topSites.get(httpLogLine.getEndpoint()) == null) {
                        topSites.put(httpLogLine.getEndpoint(), 0);
                    } else {
                        topSites.put(httpLogLine.getEndpoint(), topSites.get(httpLogLine.getEndpoint()) + 1);
                    }
                }
            }
            ValueComparator bvc1 = new ValueComparator(topSites);
            TreeMap<String, Integer> sorted_map_sites = new TreeMap<String, Integer>(bvc1);
            sorted_map_sites.putAll(topSites);
            int count1 = 0;
            List<TopSite> topSitesList = new ArrayList<>();
            for (Map.Entry<String, Integer> entry : sorted_map_sites.entrySet()) {
                if (count1 >= 5) break;
                topSitesList.add(new TopSite(entry.getKey(), entry.getValue()));
                count1++;
            }
            topHost.setTopSites(topSitesList);
        }
        return topHostList;
    }
}
