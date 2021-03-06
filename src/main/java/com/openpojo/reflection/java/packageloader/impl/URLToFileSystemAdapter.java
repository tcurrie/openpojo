/*
 * Copyright (c) 2010-2015 Osman Shoukry
 *
 *    This program is free software: you can redistribute it and/or modify
 *    it under the terms of the GNU Lesser General Public License as published by
 *    the Free Software Foundation, either version 3 of the License or any
 *    later version.
 *
 *    This program is distributed in the hope that it will be useful,
 *    but WITHOUT ANY WARRANTY; without even the implied warranty of
 *    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *    GNU Lesser General Public License for more details.
 *
 *    You should have received a copy of the GNU Lesser General Public License
 *    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.openpojo.reflection.java.packageloader.impl;

import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import com.openpojo.reflection.exception.ReflectionException;

/**
* @author oshoukry
*/
public class URLToFileSystemAdapter {
    private static final String FILE_PROTOCOL="file";
    private static final String JAR_PROTOCOL="jar";
    private static final String PROTOCOL_SEPARATOR ="://";

    private String protocol;
    private final String authority;
    private final String host;
    private final String ref;
    private final String query;
    private String path;
    private final int port;

    public URLToFileSystemAdapter(final URL url) {
        if (url == null)
            throw ReflectionException.getInstance("Null URL not allowed");

        if (url.getAuthority() != null && url.getAuthority().length() > 0)
            this.authority = url.getAuthority();
        else
            this.authority = null;
        this.host = url.getHost();
        this.ref = url.getRef();
        this.query = url.getQuery();
        this.port = url.getPort();
        this.protocol = url.getProtocol();
        this.path = decodeString(url.getPath());
        fixProtocolAndPath();
    }

    /*
     * Nasty hack because URI doesn't understand jar:file:// scheme due to the following:
     * 1. Scheme "jar" is not a protocol
     * 2. Scheme-specific-part starts with file:// and therefore isn't treated as hierarchical
     *
     * The fix here is to switch the protocol to file://.
     */
    private void fixProtocolAndPath() {
        if (protocol.equals(JAR_PROTOCOL) && path.startsWith(FILE_PROTOCOL)) {
            this.protocol = FILE_PROTOCOL;
            this.path = path.substring(FILE_PROTOCOL.length() + PROTOCOL_SEPARATOR.length());
        }
    }

    public URI getAsURI() {
        try {
            return new URI(protocol, authority, host, port, path, query, ref);
        } catch (final URISyntaxException uriSyntaxException) {
            throw ReflectionException.getInstance(uriSyntaxException.getMessage(), uriSyntaxException);
        }
    }

    public File getAsFile() {
        URI uri = getAsURI();

        // to handle windows paths i.e. //host_server/path/class, need a way to put the authority section back in
        // the path

        File directory;
        if (uri.getAuthority() != null) directory = new File("//" + uri.getAuthority() + uri.getPath());
        else directory = new File(uri.getPath());
        return directory;
    }

    private static String decodeString(String path) {

        int pos = 0;
        StringBuilder decodedString = new StringBuilder(path.length());
        while (pos < path.length()) {
            char ch = path.charAt(pos);
            if (ch == '%' && pos + 2 < path.length()) {
                String hexStr = path.substring(pos + 1, pos + 3);
                pos += 2;
                ch = (char)Integer.parseInt(hexStr, 16);
            }
            decodedString.append(ch);
            pos++;
        }
        return decodedString.toString();
    }
}
