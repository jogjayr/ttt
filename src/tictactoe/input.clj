(ns tictactoe.input)

(defn get-input [format-input validate-input fail-message]
  (loop []
    (let [input (format-input (read-line))]
      (if (validate-input input)
        input
        (do 
          (println input fail-message)
          (recur))))))

