package com.example.grpc.client;

import com.example.learn.HelloRequest;
import com.example.learn.HelloResponse;
import com.example.learn.HelloServiceGrpc;
import com.example.learn.Sentiment;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;

public class MyGrpcClient {
    public static void main(String[] arg) {
        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("192.168.0.103", 8080)
                .usePlaintext(true)
                .build();

        HelloServiceGrpc.HelloServiceStub stub = HelloServiceGrpc.newStub(channel);

        HelloRequest requestMeddate = HelloRequest.newBuilder()
                .setName("Shuza")
                .setAge(23)
                .setSentiment(Sentiment.HAPPY)
                .build();

        stub.greet(requestMeddate, new StreamObserver<HelloResponse>() {
            public void onNext(HelloResponse helloResponse) {
                System.out.println(helloResponse);
            }

            public void onError(Throwable throwable) {
                System.out.println("Error:  " + throwable.getMessage());
            }

            public void onCompleted() {
                System.out.println("on Completed");
            }
        });

        Scanner input = new Scanner(System.in);
        input.nextInt();
    }
}
