package com.sdl;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

@Path("/ngram")
public class Ngram {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public List<NgramResponse> getNgrams(NgramMessage m) {
        String s = m.getText();
        String[] tokens = s.split(" ");
        List<NgramResponse> response = new ArrayList<NgramResponse>();
        for(int n = m.getMaxNGramCount(); n > 0; n--) {
            if (n <= tokens.length) {
                HashMap<String, Integer> map = new HashMap<String, Integer>();

                StringBuilder sb = new StringBuilder();
                map.put(createFirstNgram(sb, n, tokens), 1);

                for(int i = n; i < tokens.length; i++) {
                    String currentNgram = getNextNgram(sb, i, n, tokens);
                    if(map.containsKey(currentNgram)) {
                        map.put(currentNgram, map.get(currentNgram) + 1);
                    } else {
                        map.put(currentNgram, 1);
                    }
                }

                List<NgramResponse> tempList = new ArrayList<NgramResponse>();
                for(String key : map.keySet()) {
                    tempList.add(new NgramResponse(key, map.get(key)));
                }

                Collections.sort(tempList);

                for(NgramResponse ngramResponse : tempList) {
                    response.add(ngramResponse);
                }
            }
        }
        return response;
    }

    private String createFirstNgram(StringBuilder sb, int n, String[] tokens) {
        sb.append(tokens[0]);
        for(int i = 1; i < n; i++) {
            sb.append(" ");
            sb.append(tokens[i]);
        }
        return sb.toString();
    }

    private String getNextNgram(StringBuilder sb, int current, int n, String[] tokens) {
        sb.delete(0, tokens[current-n].length() + 1);
        if (n > 1) {
            sb.append(" ");
        }
        sb.append(tokens[current]);
        return sb.toString();
    }

}
