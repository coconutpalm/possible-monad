package com.coconut_palm_software.possible.iterable;


/**
 * A basic implementation of the monadic operations over Java's Iterable&lt;T&gt;.
 * <p>
 * <code>
 * 	public static List&lt;Tuple&lt;String&gt;&gt; convertFileContentsToTrigrams(List&lt;String&gt; linesInFile) {
 *		return iterateOver(linesInFile)
 *				.transformAndFlatten(new ConvertLineToWordList())
 *				.transformAndFlatten(new ConvertWordsToTrigrams())
 *				.result();
 *	}
 *</code>
 *
 * @author djo
 *
 * @param <R> The result type
 */
public class FluentIterable<R> {

	private Iterable<R> source;

	private FluentIterable(Iterable<R> source) {
		this.source = source;
	}

	public static <A> FluentIterable<A> iterateOver(Iterable<A> source)
	{
		return new FluentIterable<A>(source);
	}

	@SuppressWarnings("unchecked")
	public <T extends Iterable<R>> T result() { return (T) source; }

	@SuppressWarnings("unchecked")
	public <Dest, IntermediateResults extends Iterable<Dest>, DestResults extends Iterable<Dest>>
		FluentIterable<Dest> transformAndConcat(F<R, IntermediateResults> func)
	{
		Iterable<Dest> result = transformAndConcat(source, func);
		return new FluentIterable<Dest>((DestResults) result);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	/* testable */<Source, SourceResults extends Iterable<Source>,
	Dest, IntermediateResults extends Iterable<Dest>, DestResults extends Iterable<Dest>>
		DestResults transformAndConcat(SourceResults source, F<Source, IntermediateResults> func)
	{
		UnitFunction resultContainer = new UnitFunction(source.getClass());
		transform(source, func, resultContainer);
		return (DestResults) resultContainer.result();
	}

	@SuppressWarnings("rawtypes")
	private <Dest, IntermediateResults extends Iterable<Dest>, Source, SourceResults extends Iterable<Source>>
		void transform(SourceResults source, F<Source, IntermediateResults> func,
						UnitFunction ResultContainer)
	{
		for (Source a : (Iterable<Source>)source) {
			IntermediateResults intermediateResults = func.apply(a);
			concat(ResultContainer, intermediateResults);
		}
	}

	@SuppressWarnings("rawtypes")
	private <Dest, IntermediateResults extends Iterable<Dest>>
		void concat(UnitFunction results, IntermediateResults intermediateResults)
	{
		for (Dest intermediateResult : intermediateResults) {
			results.add(intermediateResult);
		}
	}

    public <Dest> Dest reduce(F2<Dest, R, Dest> func, Dest initialValue) {
        Dest accumulator = initialValue;
        for (R element : source) {
            accumulator = func.apply(accumulator, element);
        }
        return accumulator;
    }
}

