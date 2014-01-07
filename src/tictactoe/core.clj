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
    (tictactoe.asciiboard/draw-board board)
    (let [winner (tictactoe.logic/is-over? board)
          next-input (tictactoe.input/get-input #(try (java.lang.Integer/parseInt %) (catch NumberFormatException e "Enter a number between 0 and 8")) #(< -1 % 8) "error")
          next-move (tictactoe.logic/compute-next-move "x" next-input board)]
      (tictactoe.asciiboard/draw-board next-move)
      (if (not winner)
        (do
          (recur next-move))
        (if (= "x" winner)
          (println "Congratulations! You won!")
          (if (= "" winner)
            (println "How boring playing for a draw")
            (println "Commiserations. You lost :-( ")))))))
