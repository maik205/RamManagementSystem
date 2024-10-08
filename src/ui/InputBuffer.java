package ui;

import utils.Observable;

public class InputBuffer extends Observable<Character> {
    private StringBuilder buffer = new StringBuilder();
    
    /**
     * Add a character to the buffer
     * @param c Character to add
     */
    public void addBuffer(Character c) {
        if (c == '\b') {
            if (this.buffer.length() > 0)
                this.buffer.deleteCharAt(this.buffer.length() - 1);
            return;
        }
        this.buffer.append(c);
    }

    /**
     * Set the buffer to a specific string
     * @param buffer String to set the buffer to
     */
    public void setBuffer(String buffer) {
        this.buffer = new StringBuilder(buffer);
    }

    /**
     * Clears the buffer
     */
    public void clearBuffer() {
        this.buffer.delete(0, this.buffer.length());
    }

    /**
     * Get the buffer as a string
     * @return String representation of the buffer
     */
    public String getBuffer() {
        return this.buffer.toString();
    }
}
