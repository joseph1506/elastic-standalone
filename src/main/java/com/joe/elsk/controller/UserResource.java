package com.joe.elsk.controller;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.transport.TransportClient;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.InetSocketTransportAddress;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import static org.elasticsearch.common.xcontent.XContentFactory.jsonBuilder;

@RestController
@RequestMapping("/rest/users")
public class UserResource {

    @GetMapping("/fetch")
    public Object getUsers() throws IOException {

        TransportClient client = new PreBuiltTransportClient(Settings.EMPTY)
                .addTransportAddress(new InetSocketTransportAddress(InetAddress.getByName("localhost"),9201));

        IndexResponse response = client.prepareIndex("employee","id", "1")
                .setSource(jsonBuilder()
                        .startObject()
                        .field("name","Joseph")
                        .field("salary", 2000)
                        .field("Team", "BASE")
                        .endObject()
                )
                .get();
        return response.getResult().toString();
    }

}
