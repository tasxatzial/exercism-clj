(ns hexadecimal)

(defn hex-char->dec
  "Returns the decimal value of a lowercase hexadecimal char."
  [c]
  (let [val (int c)]
    (if (<= 48 val 57)
      (- val 48)
      (+ 10 (- val 97)))))

(defn hex->dec-values
  "Converts a string that represents a non-negative hexadecimal
  number to a sequence of the decimal values of its digits."
  [s]
  (map hex-char->dec s))

(defn valid-hex?
  "Returns true if the given decimal values represent a valid
  hexadecimal number, false otherwise."
  [decimal-values]
  (every? #(<= 0 % 15) decimal-values))

(defn compute-decimal
  "Converts the given decimal values to the corresponding base 10 number
  assuming they represent a hexadecimal number."
  [decimal-values]
  (when (valid-hex? decimal-values)
    (reduce #(+ %2 (* 16 %1))
            0 decimal-values)))

(defn hex-to-int
  "Converts the given hex number (string) to the decimal equivalent.
  Returns 0 if the string does not represent a valid hex number."
  [s]
  (let [dec-values (hex->dec-values s)]
    (or (compute-decimal dec-values) 0)))
