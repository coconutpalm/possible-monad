/******************************************************************************
 * Copyright (c) David Orme and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Orme - initial API and implementation
 ******************************************************************************/
package com.coconut_palm_software.possible;

import org.eclipse.core.runtime.IStatus;

import com.coconut_palm_software.possible.internal.None;
import com.coconut_palm_software.possible.internal.Some;


/**
 * A Java implementation of the "Option Monad" design pattern.  (Note that this
 * particular implementation isn't actually a full monad, but for our purposes
 * it doesn't matter.)  
 * <p>
 * This is inspired by the following blog:
 * http://www.codecommit.com/blog/scala/the-option-pattern and also 
 * Functional Java (http://www.functionaljava.org)'s Option implementation.
 * In addition, since this version is more of a Java collection in its idiomatic
 * style, I have chosen to call it Possible<T> so that the static factory 
 * method names work both when called in the fully-qualified form as well as
 * when invoked using a static import.
 * <p>
 * The Option pattern provides a unified method for coding functions that could
 * return/accept a value or also might fail to produce a value.
 * <p>
 * Alternatively, you can think of an Option as a container that can contain at most
 * one element.  Our implementation takes this approach, and provides API that is 
 * designed to make sense to any Java programmer looking for a container/Iterable
 * with these properties.  To this end, we name the default type Possible<T>,
 * and provide static factory methods on Possible<T> to construct instances of the
 * type rather than directly accessing Some<T> and None<T> (as one would if one
 * had case classes and pattern matching).
 * <p>
 * This pattern makes explicit that a method might not return a value, eliminating
 * guesswork about if the method might return null when reading/learning APIs.
 *
 * @param <T> The type the Possible encapsulates
 */
public abstract class Possible<T> implements Iterable<T> {
	/**
	 * A convenience factory method meant to be imported statically and that
	 * eliminates a lot of the boilerplate that Java generics impose.
	 * 
	 * @param <T> The type of Possible object to create.  Usually inferred 
	 * automatically by the compiler.
	 * @param value The value to return.
	 * @return a new object containing the specified value.
	 */
	public static <T> Possible<T> value(T value) { return new Some<T>(value); }
	
	/**
	 * A convenience factory method meant to be imported statically and that
	 * eliminates a lot of the boilerplate that Java generics impose.
	 * 
	 * @param <T> The type of Possible object to create.  Usually inferred 
	 * automatically by the compiler.
	 * @param value The value to return.
	 * @param status The IStatus containing extra information (possibly for logging).
	 * @return a new object containing the specified value.
	 */
	public static <T> Possible<T> value(T value, IStatus status) { return new Some<T>(value, status); }
	
	/**
	 * A convenience factory method that eliminates a lot of the boilerplate that Java 
	 * generics impose and makes code using the Possible pattern read more nicely.
	 * 
	 * @param <T> The type of Possible object to create.  Usually inferred 
	 * automatically by the compiler.
	 * @return an empty container.
	 */
	public static <T> Possible<T> emptyValue() { return new None<T>(); }
	
	/**
	 * A convenience factory method that eliminates a lot of the boilerplate that Java 
	 * generics impose and makes code using the Possible pattern read more nicely.
	 * 
	 * @param <T> The type of Possible object to create.  Usually inferred 
	 * automatically by the compiler.
	 * @param reason An IStatus containing a reason for the empty value.
	 * @return an empty container containing the specified status.
	 */
	public static <T> Possible<T> emptyValue(IStatus reason) {
		return new None<T>(reason);
	}
	
	/**
	 * Return true if this container contains the specified object and false otherwise.
	 * 
	 * @param o The object to check
	 * @return true if this container contains the specified object and false otherwise.
	 */
	abstract public boolean contains(Object o);

	/**
	 * Return true if this container has no elements inside it and false otherwise.
	 * 
	 * @return true if this container has no elements inside it and false otherwise.
	 */
	abstract public boolean isEmpty();

    /**
     * Return true if this Possible contains a value or false if it is empty.
     * 
     * @return true if this Possible contains a value or false if it is empty.
     */
	abstract public boolean hasValue();
    
    /**
     * Return 0 if this container is empty and 1 otherwise.
     * 
     * @return 0 if this container is empty and 1 otherwise.
     */
	abstract public int size();

	/**
	 * Returns the contents of the container as an Object array.
	 * 
	 * @return the contents of the container as an Object array.
	 */
	abstract public Object[] toArray();

	/**
	 * Return the contents of the container as a typed array of the specified type.
	 * <p>
	 * The runtime type of the returned array is that of the specified array. If the 
	 * specified array's size is >= 1, it is returned therein. Otherwise, a new 
	 * array is allocated with the runtime type of the specified array and size==1.
	 * <p>
	 * If the array.size > 1, the element in the array immediately following the 
	 * end of the list is set to null. (This is purely to match the contract of
	 * java.util.List.
	 * <p>
	 * Like the toArray() method, this method acts as bridge between array-based 
	 * and collection-based APIs. Further, this method allows precise control over 
	 * the runtime type of the output array, and may, under certain circumstances, 
	 * be used to save allocation costs.
	 * <p>
	 * Suppose x is a Possible known to contain only strings. The following code can be 
	 * used to dump the Possible into a newly allocated array of String: 
	 * String[] y = x.toArray(new String[0]);  Note that toArray(new Object[0]) is 
	 * identical in function to toArray().
	 *  
	 * @param a the array into which the elements of the Possible are to be stored, 
	 * if it is big enough; otherwise, a new array of the same runtime type is 
	 * allocated for this purpose.
	 * 
	 * @return an array containing the Possible element, if any.
	 * @throws java.lang.ArrayStoreException if the runtime type of the specified 
	 * array is not a supertype of the runtime type of any element in this container
	 * @throws java.lang.NullPointerException if the specified array is null
	 */
	abstract public <A> A[] toArray(A[] a);

	/**
     * Return the value inside the Possible, or throw an UnsupportedOperationException
     * if there is no value.
     * 
     * @return the encapsulated T or throw UnsupportedOperationException if empty
     */
	abstract public T get();
    
    /**
     * Return the encapsulated instance of T, if there is one, or alternatively
     * return the defaultValue if there is no encapsulated T.
     * 
     * @param defaultValue The default value to return if there is no T in the 
     * container.
     * 
     * @return the encapsulated instance of T, if there is one, or alternatively
     * return the defaultValue if there is no encapsulated T.
     */
	abstract public T getOrSubstitute(T defaultValue);
    
    /**
     * Return the encapsulated instance of T, if there is one.  If the 
     * container is empty, throw the passed exception.
     * 
     * @param <E> The type of exception to throw.
     * @param exception The exception to throw.
     * @return the encapsulated instance of T, if there is one.  If the 
     * container is empty, throw the passed exception.
     * @throws E The exception type that could be thrown.
     */
	abstract public <E extends Throwable> T getOrThrow(E exception) throws E;
    
    /**
     * Return Status.OK_STATUS on success or possibly an IStaus with information
     * suitable for logging.  If this IStatus instanceof None, then the IStatus
     * is Status.CANCEL_STATUS or possibly a more detailed IStatus with the reason for
     * the failure.  This reason might be logged, thrown as an exception, or both.
     * 
     * @return IStatus The success/failure reason.
     */
	abstract public IStatus getStatus();
}
 
