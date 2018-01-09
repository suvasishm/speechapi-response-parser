package com.suvasishm.speechapi.transcript;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;

public class PrepareTranscript {
    public static void main(String[] args) throws IOException {
        FileReader fr = new FileReader(args[0]);
        BufferedReader br = new BufferedReader(fr);

        JsonNode transcriptNode = new ObjectMapper().readTree(br);
        JsonNode results = transcriptNode.get("response").get("results");

        String file = args[0].replace(".json", "_transcript.txt");
        FileWriter fw = new FileWriter(file);
        BufferedWriter bw = new BufferedWriter(fw);

        for (JsonNode result: results) {
            bw.write(result.get("alternatives").get(0).get("transcript").asText() + "\n\n");
        }

        bw.close();

        System.out.println("Transcript has been saved to: " + file);
    }
}
