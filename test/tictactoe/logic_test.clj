(ns tictactoe.logic-test
  (:require [clojure.test :refer :all] 
			[tictactoe.logic :refer :all]))

(deftest next-move-insert
  (testing "Next move is inserted into board state"
  	(compute-next-move "x" 8)
    (let [last-move (last board-state)]
      (is (= "x" (last-move 8))))))