package org.kepennar.facemash.repository.components;

import org.kepennar.facemash.model.QElement;

import com.mysema.query.types.OrderSpecifier;
import com.mysema.query.types.expr.BooleanExpression;
import com.mysema.query.types.expr.NumberExpression;

public final class ElementPredicats {

	private static final QElement $ = QElement.element;
	
	
	
	public static OrderSpecifier<Integer> orderByScoreDesc() {
		return $.score.desc();
	}
	
	public static OrderSpecifier<Integer> orderByScoreAsc() {
		return $.score.asc();
	}
	
	public static BooleanExpression nameEq(String name) {
		return $.name.eq(name);
	}
	
	public static NumberExpression<Integer> sumPLay() {
		return $.played.sum();
	}
	
}
