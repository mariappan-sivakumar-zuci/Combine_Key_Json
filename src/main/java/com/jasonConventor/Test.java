package com.jasonConventor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class Test {

    public static void main(String[] args) {
        String json = "{\"content\":[{\"id\":1,\"title\":\"New first Post updated\",\"description\":\"This is the first post description updated\",\"content\":\"this is my first updated post\",\"comments\":[{\"id\":7,\"name\":\"Mariappan\",\"email\":\"mari@gmail.com\",\"body\":\"Nice \"},{\"id\":11,\"name\":\"Rajkumar\",\"email\":\"rajkuamr@gmai.com\",\"body\":\"Tummy message\"},{\"id\":13,\"name\":\"Rajkumar\",\"email\":\"rajkuamr@gmai.com\",\"body\":\"Tummy message\"},{\"id\":1,\"name\":\"Mariappan\",\"email\":\"spotmari2002@gmail.com\",\"body\":\"Very Useful video it is updated1\"},{\"id\":12,\"name\":\"Rajkumar\",\"email\":\"rajkuamr@gmai.com\",\"body\":\"Tummy message\"},{\"id\":9,\"name\":\"Mariappan\",\"email\":\"mari@gmail.com\",\"body\":\"Nice Video\"},{\"id\":8,\"name\":\"\",\"email\":\"mari@gmail.com\",\"body\":\"Nice \"},{\"id\":6,\"name\":\"Mariappan\",\"email\":\"mari@gmail.com\",\"body\":\"Nice message\"},{\"id\":5,\"name\":\"Mariappans\",\"email\":\"spotmari2002@gmail.com\",\"body\":\"Very Useful video it is updated1\"}],\"categoryId\":2},{\"id\":2,\"title\":\"New Second Post\",\"description\":\"This is the Second post description\",\"content\":\"this is second my post\",\"comments\":[{\"id\":4,\"name\":\"Mariappans\",\"email\":\"spotmari2002@gmail.com\",\"body\":\"Very Useful video it is updated1\"}],\"categoryId\":null},{\"id\":5,\"title\":\"New Five Post\",\"description\":\"This is the Five post description\",\"content\":\"this is Five my post\",\"comments\":[{\"id\":10,\"name\":\"mariappan\",\"email\":\"spotmari@gmail.com\",\"body\":\"check comment is applied or not\"}],\"categoryId\":null},{\"id\":6,\"title\":\"New Six Post\",\"description\":\"This is the Six post description\",\"content\":\"this is Six my post\",\"comments\":[],\"categoryId\":null},{\"id\":7,\"title\":\"New Seven Post\",\"description\":\"This is the Seven post description\",\"content\":\"this is Seven my post\",\"comments\":[],\"categoryId\":null},{\"id\":8,\"title\":\"New Eight Post updated\",\"description\":\"This is the Eight post description\",\"content\":\"this is Eight my post\",\"comments\":[],\"categoryId\":2},{\"id\":9,\"title\":\"New Eight Post updated version\",\"description\":\"This is the Eight post description\",\"content\":\"this is Eight my post\",\"comments\":[],\"categoryId\":null},{\"id\":10,\"title\":\"New ten Post\",\"description\":\"This is the ten post description\",\"content\":\"this is ten my post\",\"comments\":[],\"categoryId\":null},{\"id\":11,\"title\":\"New Eleven Post\",\"description\":\"This is the Eleven post description\",\"content\":\"this is Eleven my post\",\"comments\":[],\"categoryId\":null},{\"id\":12,\"title\":\"New Twelve Post\",\"description\":\"This is the Twelve post description\",\"content\":\"this is Twelve my post\",\"comments\":[],\"categoryId\":null}],\"pageNo\":0,\"pageSize\":10,\"totalElement\":25,\"totalPages\":3,\"last\":false}"; // Your JSON string goes here

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(json);

            JsonNode contentNode = rootNode.get("content");
            ObjectNode resultNode = objectMapper.createObjectNode();

            // Iterate through each content node
            for (JsonNode content : contentNode) {
                // Iterate through each field in the content
                content.fields().forEachRemaining(entry -> {
                    // Combine keys with "_" and add to the result
                    String combinedKey = "content_" + entry.getKey();
                    resultNode.put(combinedKey, entry.getValue().asText());
                });

                // Extract comments node from each content
                JsonNode commentsNode = content.get("comments");

                // Iterate through each comment node
                for (JsonNode comment : commentsNode) {
                    // Iterate through each field in the comment
                    comment.fields().forEachRemaining(entry -> {
                        // Combine keys with "_" and add to the result
                        String combinedKey = "content_comment_" + entry.getKey();
                        resultNode.put(combinedKey, entry.getValue().asText());
                    });
                }
            }

            // Convert the result to a JSON string
            String resultJson = objectMapper.writeValueAsString(resultNode);

            // Print the result
            System.out.println(resultJson);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

