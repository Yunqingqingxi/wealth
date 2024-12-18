// 添加日志拦截器
private static OkHttpClient buildClient() {
    HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
    logging.setLevel(HttpLoggingInterceptor.Level.BODY);
    
    return new OkHttpClient.Builder()
        .addInterceptor(logging)
        .addInterceptor(chain -> {
            Request request = chain.request();
            Response response = chain.proceed(request);
            // 设置响应编码为UTF-8
            MediaType contentType = response.body().contentType();
            String content = response.body().string();
            ResponseBody responseBody = ResponseBody.create(
                MediaType.parse("application/json; charset=utf-8"), 
                content
            );
            return response.newBuilder().body(responseBody).build();
        })
        .connectTimeout(30, TimeUnit.SECONDS)
        .readTimeout(30, TimeUnit.SECONDS)
        .writeTimeout(30, TimeUnit.SECONDS)
        .build();
}

public class RetrofitClient {
    // 确保这个BASE_URL是正确的，包含 http:// 或 https:// 前缀
    private static final String BASE_URL = "http://你的服务器地址/";
    
    private static Retrofit buildRetrofit() {
        return new Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(buildClient())
            .addConverterFactory(GsonConverterFactory.create(
                new GsonBuilder()
                    .setLenient()
                    .create()
            ))
            .build();
    }
} 