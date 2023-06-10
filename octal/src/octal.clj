(ns octal)

(defn oct-char->dec
  "Returns the decimal value of a lowercase octal char."
  [c]
  (- (int c) 48))

(defn oct->dec-values
  "Converts a string that represents a non-negative octal
  number to a sequence of the decimal values of its digits."
  [s]
  (map oct-char->dec s))

(defn valid-oct?
  "Returns true if the collection of decimal values represents a valid
  octal number, false otherwise."
  [decimal-values]
  (every? #(<= 0 % 7) decimal-values))

(defn compute-decimal
  "Given a collection of decimal values (0 <= val < 8) that represents
  an octal number, it computes the number in base 10."
  [decimal-values]
  (when (valid-oct? decimal-values)
    (reduce #(+ %2 (* 8 %1))
            0 decimal-values)))

(defn to-decimal
  "Converts the given octal number (string) to the decimal equivalent.
  Returns 0 if the string does not represent a valid octal number."
  [s]
  (let [dec-values (oct->dec-values s)]
    (or (compute-decimal dec-values) 0)))
