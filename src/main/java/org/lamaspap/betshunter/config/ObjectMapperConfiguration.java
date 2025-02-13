package org.lamaspap.betshunter.config;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.lamaspap.betshunter.model.leonsbet.League;
import org.lamaspap.betshunter.model.leonsbet.Region;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.*;

@Configuration
public class ObjectMapperConfiguration {

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);

        SimpleModule simpleModule = new SimpleModule();
        simpleModule.addDeserializer(Region.class, new RegionDeserializer());
        mapper.registerModule(simpleModule);

        return mapper;
    }

    public static class RegionDeserializer extends JsonDeserializer<Region> {
        @Override
        public Region deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
            JsonNode node = p.getCodec().readTree(p);

            if (node.isTextual()) {
                String regionText = node.asText();
                return new Region(null, regionText, regionText, null, null, List.of());
            }
            else if (node.isObject()) {
                String id = node.has("id") ? node.get("id").asText(null) : null;
                String name = node.has("name") ? node.get("name").asText(null) : null;
                String nameDefault = node.has("nameDefault") ? node.get("nameDefault").asText(null) : null;
                String family = node.has("family") ? node.get("family").asText(null) : null;
                String url = node.has("url") ? node.get("url").asText(null) : null;

                List<League> leagues = List.of(); // значение по умолчанию – пустой список
                if (node.has("leagues")) {
                    List<League> leaguesList = new ArrayList<>();
                    for (JsonNode leagueNode : node.get("leagues")) {
                        League league = p.getCodec().treeToValue(leagueNode, League.class);
                        leaguesList.add(league);
                    }
                    leagues = leaguesList;
                }
                return new Region(id, name, nameDefault, family, url, leagues);
            }
            return null;
        }
    }
}