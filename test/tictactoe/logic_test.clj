(ns tictactoe.logic-test
  (:require [clojure.test :refer :all] 
			[tictactoe.logic :refer :all]))

(deftest next-move-insert
  (testing "Next move is inserted into board state"
  	(compute-next-move "x" 8)
    (let [last-move (last board-state)]
      (is (= "x" (last-move 8))))))

(deftest play-same-square
	(testing "Throws an error if a previously played square is played again"
		(is (thrown? Exception (compute-next-move "x" 8) 0))))

(deftest move-square-limits
	(testing "Rejects a move square greater than 8"
		(is (thrown? AssertionError (compute-next-move "x" 9))))
	(testing "Accepts a move square less than 8 and greater than 0"
		(compute-next-move "x" 5)
		(is (= "x" ((last board-state) 5))))
	(testing "Rejects move square less than 0"
		(is (thrown? AssertionError (compute-next-move "x" -1)))))
