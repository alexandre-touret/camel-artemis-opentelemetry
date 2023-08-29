/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package info.touret.opentelemetry.producer;

import org.apache.camel.LoggingLevel;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.stereotype.Component;

@Component
public class JMSProducerCamelRouter extends RouteBuilder {


    @Override
    public void configure() throws Exception {
        restConfiguration()
                .component("servlet")
                .bindingMode(RestBindingMode.json);

        rest()
                .get("/test")
                .routeId("rest-http")
                .to("direct:test");


        from("direct:test")
                .routeId("produce-message")
                .routeDescription("Producer example")
                .setBody(constant("HELLO from Camel!"))
                .log(LoggingLevel.INFO, "New message with trace=${header.traceparent}")
                .log("HEADERS ${in.headers}")
                .log("Exchange information: ${exchange}")
                .to("activemq:queue:HELLO.WORLD?disableReplyTo=true")
                .end();
    }

}
