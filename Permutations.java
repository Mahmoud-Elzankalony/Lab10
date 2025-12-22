import java.util.Iterator;

public class Permutations implements Iterable {
    @Override
    public Iterator<Integer> iterator() {
        return new NonZeroFiveDigitNumberIterator();
    }

    private class NonZeroFiveDigitNumberIterator implements Iterator<Integer>
    {
        private int current = 11111;

        @Override
        public boolean hasNext() {
            while (current <= 99999) {
                if (isValid(current)) {
                    return true;
                }
                current++;
            }
            return false;
        }

        @Override
        public Integer next() {
            // hasNext ensures current is a valid candidate or we're at the end
            if (!hasNext()) {
                throw new java.util.NoSuchElementException();
            }
            int result = current;
            current++; // Move to the next candidate for the subsequent call
            return result;
        }

        private boolean isValid(int number) {
            String s = String.valueOf(number);
            return !s.contains("0");
        }
    }
}
