package com.coconut_palm_software.possible.iterable.fn;

import static com.coconut_palm_software.possible.iterable.FluentIterable.*;

import com.coconut_palm_software.possible.iterable.F2;

/**
 * Join multiple strings together with a separator string.
 *
 * @author dorme
 */
public class Join implements F2<String, String, String> {

    private final String separator;

    /**
     * Non-API constructor
     * @param separator
     */
    private Join(String separator) {
        this.separator = separator;
    }

    /* (non-Javadoc)
     * @see com.coconut_palm_software.possible.iterable.F2#apply(java.lang.Object, java.lang.Object)
     */
    @Override
    public String apply(String a, String b) {
        return a + separator + b;
    }

    /**
     * Join an Iterable&lt;String&gt> source by concatinating its contents with the specified
     * separator string.
     *
     * @param source The source Iterable&lt;String&gt;.
     * @param separator The separator string.
     * @return The concatinated String.
     */
    public static String join(Iterable<String> source, String separator) {
        return iterateOver(source).reduce(new Join(separator), "");
    }
}
