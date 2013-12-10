(ns tictactoe.core
  (:gen-class)
  (:require tictactoe.ui.asciiboard)
  (:require tictactoe.logic))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (tictactoe.ui/draw-board))
