(ns tictactoe.core
  (:gen-class)
  (:require tictactoe.asciiboard)
  (:require tictactoe.logic))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (tictactoe.asciiboard/draw-board []))
