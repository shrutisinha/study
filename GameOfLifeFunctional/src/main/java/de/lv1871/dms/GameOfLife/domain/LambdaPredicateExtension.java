package de.lv1871.dms.GameOfLife.domain;

import java.util.function.BiFunction;
import java.util.function.Predicate;

public class LambdaPredicateExtension {

	public static <T> Predicate<T> which(Predicate<T> leftPredicate,
			BiFunction<Predicate<T>, Predicate<T>, Predicate<T>> combiner, Predicate<T> rightPredicate) {
		return combiner.apply(leftPredicate, rightPredicate);
	}

	public static <T> BiFunction<Predicate<T>, Predicate<T>, Predicate<T>> and() {
		return (predicateLeft, predicateRight) -> predicateLeft.and(predicateRight);
	}

	public static <T> BiFunction<Predicate<T>, Predicate<T>, Predicate<T>> or() {
		return (predicateLeft, predicateRight) -> predicateLeft.or(predicateRight);
	}

}
