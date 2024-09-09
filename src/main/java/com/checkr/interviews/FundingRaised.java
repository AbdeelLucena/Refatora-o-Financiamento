package com.checkr.interviews;

import java.util.*;
import java.io.IOException;

public class FundingRaised {
    public static List<String[]> filter(List<String[]> csvData, String row, Map<String, String> options, int column) {
        List<String[]> results = new ArrayList<String[]> ();

        for(int i = 0; i < csvData.size(); i++) {
            if(csvData.get(i)[column].equals(options.get(row))) {
                results.add(csvData.get(i));
            }
        }
        return results;
    }

    public static Map<String, String> mapped(String[] row) {
        Map<String, String> mapped = new HashMap<String, String> ();
        mapped.put("permalink", row[0]);
        mapped.put("company_name", row[1]);
        mapped.put("number_employees", row[2]);
        mapped.put("category", row[3]);
        mapped.put("city", row[4]);
        mapped.put("state", row[5]);
        mapped.put("funded_date", row[6]);
        mapped.put("raised_amount", row[7]);
        mapped.put("raised_currency", row[8]);
        mapped.put("round", row[9]);
        return mapped;
    }

    public static List<Map<String, String>> where(Map<String, String> options) throws IOException {
        List<String[]> csvData = CsvData.getCsvData();

        if(options.containsKey("company_name")) {
            csvData = filter(csvData, "company_name", options, 1);
        }

        if(options.containsKey("city")) {
            csvData = filter(csvData, "city", options, 4);
        }

        if(options.containsKey("state")) {
            csvData = filter(csvData, "state", options, 5);
        }

        if(options.containsKey("round")) {
            csvData = filter(csvData, "round", options, 9);
        }

        List<Map<String, String>> output = new ArrayList<Map<String, String>>();

        for(int i = 0; i < csvData.size(); i++) {

            output.add(mapped(csvData.get(i)));
        }

        return output;
    }

    public static Map<String, String> findBy(Map<String, String> options) throws IOException, NoSuchEntryException {
        List<String[]> csvData = CsvData.getCsvData();

        for(int i = 0; i < csvData.size(); i++) {
            boolean exists = false;
            if(options.containsKey("company_name")) {
                if(csvData.get(i)[1].equals(options.get("company_name"))) {
                    exists = true;
                } else {
                    continue;
                }
            }

            if(options.containsKey("city")) {
                if(csvData.get(i)[4].equals(options.get("city"))) {
                    exists = true;
                } else {
                    continue;
                }
            }

            if(options.containsKey("state")) {
                if(csvData.get(i)[5].equals(options.get("state"))) {
                    exists = true;
                } else {
                    continue;
                }
            }

            if(options.containsKey("round")) {
                if(csvData.get(i)[9].equals(options.get("round"))) {
                    exists = true;
                } else {
                    continue;
                }
            }
            if (exists) {
                return mapped(csvData.get(i));
            }

        }

        throw new NoSuchEntryException("");
    }
}
