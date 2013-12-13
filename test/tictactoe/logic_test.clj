(ns tictactoe.logic-test
  (:require [clojure.test :refer :all] 
      [tictactoe.logic :refer :all]))

(deftest move-validity
  (let [board ["" "" "" "" "" "" "" "" "x"]]
    (testing "Throws an Exception if a previously played square is played again"
      (is (thrown? Exception (is-valid-move? "x" 8 board) 0)))
    (testing "Throws an Exception if two x-es in a row are played"
      (is (thrown? Exception (is-valid-move? "x" 4 board))))
    (testing "Rejects a move square greater than 8"
      (is (thrown? AssertionError (is-valid-move? "x" 9 board))))
    (testing "Rejects move square less than 0"
      (is (thrown? AssertionError (is-valid-move? "x" -1 board))))))

(deftest move-occurrence-counts
  (testing "Returns 0 if no occurrence of moves in the state"
    (is (= 0 (move-count "y" board-state))))
  (testing "Returns 2 if 2 occurrences of 'x' in the state"
    (is (= 2 (move-count "x" ["x" "o" "o" "" "" "x" "" ""])))))

(deftest correct-move-played
  (testing "Plays center square if it isn't played"
    (let [board [(vec (repeat 9 ""))]]
      (is (= ["" "" "" "x" "o" "" "" "" ""] (compute-next-move "x" 3 board)))))
  (testing "plays a corner square if center square is played")
    (let [board [["" "" "" "x" "o" "" "" "" ""]]]
      (is (= ["o" "x" "" "x" "o" "" "" "" ""] (compute-next-move "x" 1 board) ))
      (is (= ["o" "x" "o" "x" "o" "" "" "x" ""] (compute-next-move "x" 7 [["o" "x" "" "x" "o" "" "" "" ""]])))))

