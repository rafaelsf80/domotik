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
class Database {

    String TAG = getClass().getSimpleName();

    private String model;
    private String type;
    private String ram;
    private String processor;
    private String screen;
    private String hardDisk;
    private String urlPhoto;

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public String getScreen() {
        return screen;
    }

    public void setScreen(String screen) {
        this.screen = screen;
    }

    public String getHardDisk() {
        return hardDisk;
    }

    public void setHardDisk(String hardDisk) {
        this.hardDisk = hardDisk;
    }

    public String getUrlPhoto() {
        return urlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }

    public Database(String hwAddress) {

        //iPad2 70:DE:E2:03:49:B2
        if (hwAddress.contains("70:DE:E2:03:49:B2")) {
            setModel("Apple iPad2");
            setType("Tablet");
            setRam("1 GB");
            setProcessor("A5??");
            setScreen("1920x1200??");
            setHardDisk("16 GB");
            setUrlPhoto("http://store.storeimages.cdn-apple.com/4662/as-images.apple.com/is/image/AppleInc/aos/published/images/i/pa/ipad/air/ipad-air-witb-gray-cel-201410?wid=366&hei=519&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=BQ2k73");
        }

        // MacBook Pro A4:5E:60:DB:CF:15
        if (hwAddress.contains("A4:5E:60:DB:CF:15")) {
            setModel("MacBook Pro 13''");
            setType("Laptop");
            setRam("16 GB");
            setProcessor("A5??");
            setScreen("1920x1200??");
            setHardDisk("128 GB");
            setUrlPhoto("http://store.storeimages.cdn-apple.com/4662/as-images.apple.com/is/image/AppleInc/aos/published/images/M/AC/MACBOOKPRO/MACBOOKPRO?wid=1200&hei=630&fmt=jpeg&qlt=95&op_sharpen=0&resMode=bicub&op_usm=0.5,0.5,0,0&iccEmbed=0&layer=comp&.v=6xyk93");
        }

        // iPhone4
        if (hwAddress.contains("XX:XX:XX:XX:XX:XX")) {
            setModel("iPhone4");
            setType("Smartphone");
            setRam("16 GB");
            setProcessor("A5??");
            setScreen("1920x1200??");
            setHardDisk("16 GB");
            setUrlPhoto("http://www6.pcmag.com/media/images/267059-apple-iphone-4-at-t.jpg?thumb=y");
        }

        // iPad3
        if (hwAddress.contains("74:E1:B6:85:98:69")) {
            setModel("Apple iPAD 3");
            setType("Tablet");
            setRam("1 GB");
            setProcessor("A5??");
            setScreen("1920x1200??");
            setHardDisk("16 GB");
            setUrlPhoto("http://www.mobileworldni.com/wp-content/uploads/2016/03/ipad-3.jpg");
        }

        // Nexus 6
        if (hwAddress.contains("44:80:EB:67:E8:14")) {
            setModel("Motorola Nexus 6");
            setType("Smartphone");
            setRam("2 GB");
            setProcessor("A5??");
            setScreen("1920x1200??");
            setHardDisk("32 GB");
            setUrlPhoto("http://motogeros.com/wp-content/uploads/2015/03/Nexus-6.png");
        }

        // Samsung TV 14:49:E0:14:CC:C7
        if (hwAddress.contains("14:49:E0:14:CC:C7")) {
            setModel("Samsung UE22H5610A LED 22 HD");
            setType("TV");
            setRam("? GB");
            setProcessor("");
            setScreen("1920x1200 16:9");
            setHardDisk("?? GB");
            setUrlPhoto("http://i.kinja-img.com/gawker-media/image/upload/z58srdx66vqlxkxhsmhu.png");
        }

        // Huawei
        if (hwAddress.contains("08:19:A6:CA:3C:B0")) {
            setModel("Huawei ONT");
            setType("Fiber Terminal Unit (ONT)");
            setRam("? GB");
            setProcessor("??");
            setScreen("1920x1200??");
            setHardDisk("?? GB");
            setUrlPhoto("http://g01.s.alicdn.com/kf/HTB1N_cRJFXXXXaBXpXXq6xXFXXXD/202167021/HTB1N_cRJFXXXXaBXpXXq6xXFXXXD.jpg");
        }
    }
}
//                Document doc = Jsoup.parse(s);
//
//                Element table = doc.select("table").get(0); //select the first table.
//
//                for (Element row : table.select("tr")) {
//                    Machine tmp = new Machine();
//                    Elements tds = row.select("td");
//                    tmp.setIpAddress(tds.get(0).text());
//                    tmp.setFlags(tds.get(1).text());
//                    tmp.setHwAddress(tds.get(2).text());
//                    tmp.setPort(tds.get(3).text());
//
//
//
//
//                    mAdapter.add(tmp);
//                    Log.d(TAG, "Machine added: " + tmp.getIpAddress() + " " + tmp.getFlags() + " " + tmp.getHwAddress() + " " + tmp.getPort());
//                }
//                mAdapter.notifyDataSetChanged();
//
//            }
//        }, new Response.ErrorListener() {
//
//            @Override
//            public void onErrorResponse(VolleyError volleyError) {
//                Log.d(TAG, "Response: " + volleyError.toString());
//            }
//        })
//
//        {
//            @Override
//            public Map<String, String> getHeaders() throws AuthFailureError {
//                Map<String, String> headers = new HashMap<String, String>();
//                // add headers <key,value>
//                String credentials = USERNAME + ":" + PASSWORD;
//                String auth = "Basic "
//                        + Base64.encodeToString(credentials.getBytes(),
//                        Base64.NO_WRAP);
//                headers.put("Authorization", auth);
//                return headers;
//            }
//        };
//
//        mQueue.add(mRequest);
//    }
//
//
//    String testResponse = "<html><head>\n" +
//            "<link rel=stylesheet href='stylemain.css' type='text/css'>\n" +
//            "<link rel=stylesheet href='colors.css' type='text/css'>\n" +
//            "<meta HTTP-EQUIV='Pragma' CONTENT='no-cache'>\n" +
//            "<title></title>\n" +
//            "</head>\n" +
//            "<body>\n" +
//            "<blockquote>\n" +
//            "<form>\n" +
//            "<b>Device Info -- ARP</b><br><br>\n" +
//            "<table border='1' cellpadding='4' cellspacing='0'>\n" +
//            "<tr>\n" +
//            "<td class='hd'>IP address</td>\n" +
//            "<td class='hd'>Flags</td>\n" +
//            "<td class='hd'>HW Address</td>\n" +
//            "<td class='hd'>Device</td>\n" +
//            "</tr>\n" +
//            "<tr>\n" +
//            "<td>192.168.1.15</td>\n" +
//            "<td>Complete</td>\n" +
//            "<td>A4:5E:60:DB:CF:15</td>\n" +
//            "<td>br0</td>\n" +
//            "</tr>\n" +
//            "<tr>\n" +
//            "<td>192.168.1.18</td>\n" +
//            "<td>Complete</td>\n" +
//            "<td>70:DE:E2:03:49:B2</td>\n" +
//            "<td>br0</td>\n" +
//            "</tr>\n" +
//            "<tr>\n" +
//            "<td>10.237.218.114</td>\n" +
//            "<td>Complete</td>\n" +
//            "<td>00:02:9B:48:84:9E</td>\n" +
//            "<td>br0</td>\n" +
//            "</tr>\n" +
//            "<tr>\n" +
//            "<td>192.168.1.13</td>\n" +
//            "<td>Complete</td>\n" +
//            "<td>44:80:EB:67:E8:14</td>\n" +
//            "<td>br0</td>\n" +
//            "</tr>\n" +
//            "<tr>\n" +
//            "<td>192.168.1.11</td>\n" +
//            "<td>Complete</td>";
//
//}