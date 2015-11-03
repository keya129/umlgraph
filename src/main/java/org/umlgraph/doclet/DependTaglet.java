/*
 * Taglet registration
 *
 * (C) Copyright 2014 Diomidis Spinellis
 *
 * Permission to use, copy, and distribute this software and its
 * documentation for any purpose and without fee is hereby granted,
 * provided that the above copyright notice appear in all copies and that
 * both that copyright notice and this permission notice appear in
 * supporting documentation.
 *
 * THIS SOFTWARE IS PROVIDED ``AS IS'' AND WITHOUT ANY EXPRESS OR IMPLIED
 * WARRANTIES, INCLUDING, WITHOUT LIMITATION, THE IMPLIED WARRANTIES OF
 * MERCHANTIBILITY AND FITNESS FOR A PARTICULAR PURPOSE.
 *
 *
 */

import com.sun.tools.doclets.Taglet;
import com.sun.javadoc.*;
import java.util.Map;

/**
 * Ignore the UMLGraph @depend tag.
 * @author Diomidis Spinellis
 */
public class DependTaglet implements Taglet {

    private static final String NAME = "depend";

    /**
     * Return the name of this custom tag.
     */
    public String getName() {
        return NAME;
    }

    /**
     * Will return false, because <code>@depend</code>
     * cannot be used in field documentation.
     * @return true if <code>@depend</code>
     * can be used in field documentation and false
     * otherwise.
     */
    public boolean inField() {
        return false;
    }

    /**
     * Will return false, because <code>@depend</code>
     * cannot be used in constructor documentation.
     * @return true if <code>@depend</code>
     * can be used in constructor documentation and false
     * otherwise.
     */
    public boolean inConstructor() {
        return false;
    }

    /**
     * Will return false, because <code>@depend</code>
     * cannot be used in method documentation.
     * @return true if <code>@depend</code>
     * can be used in method documentation and false
     * otherwise.
     */
    public boolean inMethod() {
        return false;
    }

    /**
     * Will return false, because <code>@depend</code>
     * cannot be used in method documentation.
     * @return true if <code>@depend</code>
     * can be used in overview documentation and false
     * otherwise.
     */
    public boolean inOverview() {
        return false;
    }

    /**
     * Will return false, because <code>@depend</code>
     * cannot be used in package documentation.
     * @return true if <code>@depend</code>
     * can be used in package documentation and false
     * otherwise.
     */
    public boolean inPackage() {
        return false;
    }

    /**
     * Will return true, because <code>@depend</code>
     * can be used in type documentation (classes or interfaces).
     * @return true if <code>@depend</code>
     * can be used in type documentation and false
     * otherwise.
     */
    public boolean inType() {
        return true;
    }

    /**
     * Will return false, because <code>@depend</code>
     * is not an inline tag.
     * @return false if <code>@depend</code>
     * is not an inline tag.
     */

    public boolean isInlineTag() {
        return false;
    }

    /**
     * Register this Taglet.
     * @param tagletMap  the map to register this tag to.
     */
    public static void register(Map tagletMap) {
       DependTaglet tag = new DependTaglet();
       Taglet t = (Taglet) tagletMap.get(tag.getName());
       if (t != null) {
           tagletMap.remove(tag.getName());
       }
       tagletMap.put(tag.getName(), tag);
    }

    /**
     * Given the <code>Tag</code> representation of this custom
     * tag, return its string representation.
     * @param tag   the <code>Tag</code> representation of this custom tag.
     */
    public String toString(Tag tag) {
        return "";
    }

    /**
     * Given an array of <code>Tag</code>s representing this custom
     * tag, return its string representation.
     * @param tags  the array of <code>Tag</code>s representing of this custom tag.
     */
    public String toString(Tag[] tags) {
        if (tags.length == 0) {
            return null;
        }
        return "";
    }
}
