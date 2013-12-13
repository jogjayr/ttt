(ns tictactoe.logic-test
  (:require [clojure.test :refer :all] 
			[tictactoe.logic :refer :all]))

(deftest next-move-insert
  (testing "Next move is inserted into board state"
  	(compute-next-move "x" 8)
    (let [last-move (last board-state)]
      (is (= "x" (last-move 8))))))

(deftest play-same-square
	(testing "Throws an Exception if a previously played square is played again"
		(is (thrown? Exception (compute-next-move "x" 8) 0)))
	(testing "Throws an Exception if two x-es in a row are played"
		(is (thrown? Exception (compute-next-move "x" 6)))))

(deftest move-square-limits
	(testing "Rejects a move square greater than 8"
		(is (thrown? AssertionError (compute-next-move "x" 9))))
	(testing "Accepts a move square less than 8 and greater than 0"
		(compute-next-move "o" 5)
		(is (= "o" ((last board-state) 5))))
	(testing "Rejects move square less than 0"
		(is (thrown? AssertionError (compute-next-move "x" -1)))))

(deftest move-occurrence-counts
	(testing "Returns 0 if no occurrence of moves in the state"
		(is (= 0 (move-count "y" board-state))))
	(testing "Returns 2 if 2 occurrences of 'x' in the state"
		(is (= 2 (move-count "x" ["x" "o" "o" "" "" "x" "" ""])))))

