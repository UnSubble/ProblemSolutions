class Solution {
    public boolean isNumber(String s) {
        return s.toLowerCase().replaceFirst("^[+-]?((\\.\\d+)|(\\d+\\.\\d*)|(\\d+))(e[+-]?\\d+)?$","").isEmpty();
    }
}