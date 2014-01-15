(ns tictactoe.core
  (:gen-class)
  (:require tictactoe.asciiboard)
  (:require tictactoe.logic)
  (:require tictactoe.input))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (tictactoe.asciiboard/draw-board [])
  (println "Welcome to Clojure tictactoe! You are 'x', the computer is 'o'. You go first. Enter a move: ")
  (loop [board (vec(repeat 9 ""))]
    (let [next-input (tictactoe.input/get-input #(try (java.lang.Integer/parseInt %) (catch NumberFormatException e "Enter a number between 0 and 8")) #(< -1 % 9) "error")
          board-after-input (assoc board next-input "x")
          winner-after-input (tictactoe.logic/is-over? board-after-input)]
      (tictactoe.asciiboard/draw-board board-after-input)
      (if (not winner-after-input)
        (let [next-move (tictactoe.logic/compute-next-move "x" next-input board)
              winner-after-computer-move (tictactoe.logic/is-over? next-move) ]
          (println "Computer plays")
          (tictactoe.asciiboard/draw-board next-move)
          (if (not winner-after-computer-move) 
            (recur next-move)
            (if (= "" winner-after-computer-move)
              (println "How boring playing for a draw")
              (println "Commiserations. You lost :-( "))))
        (if (= "" winner-after-input)
          (println "How boring playing for a draw")
          (println "Congratulations! You won!"))))))
      