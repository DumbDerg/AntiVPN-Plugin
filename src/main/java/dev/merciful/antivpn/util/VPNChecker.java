package dev.merciful.antivpn.util;

import dev.merciful.antivpn.AntiVPN;

public class VPNChecker {
    /**
     * Checks if the ip is the VPN/Proxy by using https://proxycheck.io API<3
     */
    public static Boolean checkIP(String ip, AntiVPN plugin){
        String url = "http://proxycheck.io/v2/"+ip+"?key="+plugin.getConfig().getString("API_KEY")+"&vpn=3";
        return false; // TODO: 8/31/2023 Change this!<3
    }


}
