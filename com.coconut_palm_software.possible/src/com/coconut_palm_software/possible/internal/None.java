/******************************************************************************
 * Copyright (c) David Orme and others
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    David Orme - initial API and implementation
 ******************************************************************************/
package com.coconut_palm_software.possible.internal;

import java.util.Iterator;

import com.coconut_palm_software.possible.Possible;
import com.coconut_palm_software.possible.iterable.F;


/**
 * An Possible instance that does not contain any value.
 *
 * @param <T> The type that this Possible&lt;T&gt; is encapsulating.
 */
public final class None<T> extends Possible<T> {

    private Object status = null;

    public None() {}

	public None(Object status) {
		this.status = status;
	}

    /* (non-Javadoc)
     * @see org.eclipse.e4.core.functionalprog.optionmonad.Option#get()
     */
    public T get() {
        throw new UnsupportedOperationException("Cannot resolve value on None");
    }

	/* (non-Javadoc)
	 * @see org.eclipse.e4.core.functionalprog.optionmonad.Option#getOrSubstitute(java.lang.Object)
	 */
	public T getOrSubstitute(T defaultValue) {
		return defaultValue;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.e4.core.functionalprog.optionmonad.Option#getOrThrow(java.lang.Throwable)
	 */
	public <E extends Throwable> T getOrThrow(E exception) throws E {
		throw exception;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.e4.core.functionalprog.optionmonad.Option#hasValue()
	 */
	public boolean hasValue() {
		return false;
	}

	/* (non-Javadoc)
	 * @see org.eclipse.e4.core.functionalprog.optionmonad.Option#getReason()
	 */
    @SuppressWarnings("unchecked")
    public <S> S getStatus() {
		return (S) status;
	}

	/* (non-Javadoc)
	 * @see com.coconut_palm_software.possible.Possible#contains(java.lang.Object)
	 */
	@Override
	public boolean contains(Object o) {
		return false;
	}

	/* (non-Javadoc)
	 * @see com.coconut_palm_software.possible.Possible#isEmpty()
	 */
	@Override
	public boolean isEmpty() {
		return true;
	}

	/* (non-Javadoc)
	 * @see com.coconut_palm_software.possible.Possible#size()
	 */
	@Override
	public int size() {
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.coconut_palm_software.possible.Possible#toArray()
	 */
	@Override
	public Object[] toArray() {
		return new Object[] {};
	}

	/* (non-Javadoc)
	 * @see com.coconut_palm_software.possible.Possible#toArray(A[])
	 */
	@Override
	public <A> A[] toArray(A[] a) {
		return a;
	}

	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return new Iterator<T>() {

			@Override
			public boolean hasNext() {
				return false;
			}

			@Override
			public T next() {
				throw new IndexOutOfBoundsException("Empty possible value cannot have a 'next' element");
			}

			@Override
			public void remove() {
				throw new UnsupportedOperationException();
			}};
	}

    /* (non-Javadoc)
     * @see com.coconut_palm_software.possible.Possible#apply(com.coconut_palm_software.possible.iterable.F)
     */
    @Override
    public <R> Possible<R> apply(F<T, R> f) {
        return Possible.emptyValue();
    }
}