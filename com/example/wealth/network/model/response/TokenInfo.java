public class TokenInfo {
    private String token;
    private long expiresIn;

    public TokenInfo(String token, long expiresIn) {
        this.token = token;
        this.expiresIn = expiresIn;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    @Override
    public String toString() {
        return "TokenInfo{" +
                "token='" + token + '\'' +
                ", expiresIn=" + expiresIn +
                '}';
    }
} 