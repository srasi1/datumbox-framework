/**
 * Copyright (C) 2013-2016 Vasilis Vryniotis <bbriniotis@datumbox.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.datumbox.framework.core.mathematics.discrete;

import java.util.*;

/**
 * Utility class for combinations and permutations.
 *
 * @author Vasilis Vryniotis <bbriniotis@datumbox.com>
 */
public class Combinatorics {

    /**
     * Returns the permutations of a collection. 
     * Ported from:
     * http://stackoverflow.com/questions/10503392/java-code-for-permutations-of-a-list-of-numbers
     *
     * @param <T>
     * @param elements
     * @return
     */
    public static <T> Collection<List<T>> permutations(Collection<T> elements) {
        Collection<List<T>> result = new ArrayList<>();
        if (elements.isEmpty()) {
            result.add(new LinkedList<>());
            return result;
        }

        List<T> rest = new LinkedList<>(elements);
        T head = rest.remove(0);
        for (List<T> permutations : permutations(rest)) {
            List<List<T>> subLists = new ArrayList<>();
            for (int i = 0; i <= permutations.size(); i++) {
                List<T> subList = new ArrayList<>();
                subList.addAll(permutations);
                subList.add(i, head);
                subLists.add(subList);
            }
            result.addAll(subLists);
        }
        return result;
    }

    /**
     * Returns all the possible combinations of a list.
     * Ported from:
     * http://codereview.stackexchange.com/questions/26854/recursive-method-to-return-a-set-of-all-combinations
     *
     * @param elements
     * @param subsetSize
     * @param <T>
     * @return
     */
    public static <T> Set<Set<T>> combinations(Set<T> elements, int subsetSize) {
        Set<Set<T>> resultingCombinations = new HashSet<> ();
        int totalSize=elements.size();
        if (subsetSize == 0) {
            resultingCombinations.add(new HashSet<>());
        }
        else if (subsetSize <= totalSize) {
            Set<T> remainingElements = elements;

            Iterator<T> it = remainingElements.iterator();
            T X = it.next();
            it.remove();

            resultingCombinations.addAll(combinations(remainingElements, subsetSize));

            for (Set<T> combination : combinations(remainingElements, subsetSize-1)) {
                combination.add(X);
                resultingCombinations.add(combination);
            }

            remainingElements.add(X);
        }
        return resultingCombinations;
    }

}
