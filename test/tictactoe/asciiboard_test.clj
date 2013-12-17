(ns tictactoe.asciiboard_test
  (:require [clojure.test :refer :all]
    [tictactoe.asciiboard :refer :all]))

(deftest board-fill
  (testing "fill board returns empty board when board is empty"
    (is (= "            0  |  1  |  2
          _____|_____|_____
            3  |  4  |  5     
          _____|_____|_____
            6  |  7  |  8  
               |     |     \n" (fill-board [])))))