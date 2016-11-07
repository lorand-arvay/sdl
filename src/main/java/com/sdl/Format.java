package com.sdl;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/format")
public class Format {

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public FormatMessage format(FormatMessage body)  {

        FormatMessage response = new FormatMessage(parseBody(body));
        return response;
    }

    private String parseBody(FormatMessage body) {
        String[] tokens = body.getText().split(" ");
        StringBuilder sb = new StringBuilder();
        int i = 0;
        while (i < tokens.length) {
            if (tokens[i].equals("PP")) {
                int n = Integer.valueOf(tokens[i+1]);
                int m = Integer.valueOf(tokens[i+2]);
                if (i + 3 + n + m > tokens.length) {
                    throw new MessageFormatException("Missing data. Number of tokens expected larger than actual data in message body.");
                }
                if (!tokens[i + 3 + n].equals("$")) {
                    throw new MessageFormatException("Missing delimitor. $ delimitor expected after first set of tokens");
                }
                appendTokens(sb, n, i + 3, i + 3 + n, tokens);
                appendDelimiter(sb, "$");
                appendTokens(sb, m, i + 4 + n, i + 4 + n + m, tokens);
                if (i + 4 + n + m < tokens.length && tokens[i + 4 + n + m].equals("&")) {
                    appendDelimiter(sb, "&");
                    int j = i + 5 + n + m;
                    while (j < tokens.length && !tokens[j].equals("PP")) {
                        sb.append(tokens[j]);
                        sb.append(" ");
                        j++;
                    }
                    i = j;
                } else {
                    i = i + 4 + n + m;
                }
                appendDelimiter(sb, "|");
            } else {
                throw new MessageFormatException("Missing begin token. \"PP\" expected at position " + i + ".");
            }
        }
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }

    private void appendDelimiter(StringBuilder sb, String delimitor) {
        sb.append(delimitor);
        sb.append(" ");
    }

    private void appendTokens(StringBuilder sb, int length, int begin, int end, String[] tokens) {
        sb.append(length);
        sb.append(" ");
        for (int j = begin; j < end; j++) {
            sb.append(tokens[j]);
            sb.append(" ");
        }
    }

}
