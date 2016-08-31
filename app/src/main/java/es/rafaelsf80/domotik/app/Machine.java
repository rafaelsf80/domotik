package es.rafaelsf80.domotik.app;

/**
 * Copyright 2016 Rafael Sanchez Fuentes
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <p/>
 * Author: Rafael Sanchez Fuentes rafaelsf80 at gmail dot com
 */
public class Machine {

    String ipAddress;
    String flags;
    String hwAddress;
    String port;
    String name;
    String type;

    public String getIpAddress() {
        return ipAddress;
    }

    public String getFlags() {
        return flags;
    }

    public String getHwAddress() {
        return hwAddress;
    }

    public String getPort() {
        return port;
    }

    public String getName() { return name; }

    public String getType() { return type; }

    public void setIpAddress(String ipAddress) {
        this.ipAddress = ipAddress;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }

    public void setHwAddress(String hwAddress) {
        this.hwAddress = hwAddress;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }
}
