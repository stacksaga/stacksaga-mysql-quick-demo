package demo.quickdemo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class WelcomeDTO {


    @Schema(name = "WelcomeDTOResponse")
    public static class Response {
        @JsonProperty("instance_id")
        private String instanceId;
        @JsonProperty("message")
        private String message;

        @JsonProperty("region")
        private String region;

        @JsonProperty("service_name")
        private String serviceName;

        @JsonProperty("zone")
        private String zone;

        @JsonProperty("db_mode")
        private String dbMode;

        @JsonProperty("db_type")
        private String dbType;

        @JsonProperty("hint")
        private String hint;

        @JsonProperty("datetime")
        private String datetime;

        public String getServiceName() {
            return serviceName;
        }

        public void setServiceName(String serviceName) {
            this.serviceName = serviceName;
        }

        public String getDatetime() {
            return datetime;
        }

        public void setDatetime(String datetime) {
            this.datetime = datetime;
        }

        public String getDbMode() {
            return dbMode;
        }

        public void setDbMode(String dbMode) {
            this.dbMode = dbMode;
        }

        public String getDbType() {
            return dbType;
        }

        public void setDbType(String dbType) {
            this.dbType = dbType;
        }

        public String getHint() {
            return hint;
        }

        public void setHint(String hint) {
            this.hint = hint;
        }

        public String getInstanceId() {
            return instanceId;
        }

        public void setInstanceId(String instanceId) {
            this.instanceId = instanceId;
        }

        public String getRegion() {
            return region;
        }

        public void setRegion(String region) {
            this.region = region;
        }

        public String getZone() {
            return zone;
        }

        public void setZone(String zone) {
            this.zone = zone;
        }

        public String getMessage() {
            return message;
        }

        public void setMessage(String message) {
            this.message = message;
        }
    }
}
