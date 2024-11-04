package proto.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.50.2)",
    comments = "Source: trip.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class TripServiceGrpc {

  private TripServiceGrpc() {}

  public static final String SERVICE_NAME = "com.example.Service.TripService.grpc.com.example.Service.TripService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<proto.grpc.Trip.SearchTripPreviewRequest,
      proto.grpc.Trip.SearchTripPreviewResponse> getSearchTripPreviewMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "SearchTripPreview",
      requestType = proto.grpc.Trip.SearchTripPreviewRequest.class,
      responseType = proto.grpc.Trip.SearchTripPreviewResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.grpc.Trip.SearchTripPreviewRequest,
      proto.grpc.Trip.SearchTripPreviewResponse> getSearchTripPreviewMethod() {
    io.grpc.MethodDescriptor<proto.grpc.Trip.SearchTripPreviewRequest, proto.grpc.Trip.SearchTripPreviewResponse> getSearchTripPreviewMethod;
    if ((getSearchTripPreviewMethod = TripServiceGrpc.getSearchTripPreviewMethod) == null) {
      synchronized (TripServiceGrpc.class) {
        if ((getSearchTripPreviewMethod = TripServiceGrpc.getSearchTripPreviewMethod) == null) {
          TripServiceGrpc.getSearchTripPreviewMethod = getSearchTripPreviewMethod =
              io.grpc.MethodDescriptor.<proto.grpc.Trip.SearchTripPreviewRequest, proto.grpc.Trip.SearchTripPreviewResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "SearchTripPreview"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.SearchTripPreviewRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.SearchTripPreviewResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TripServiceMethodDescriptorSupplier("SearchTripPreview"))
              .build();
        }
      }
    }
    return getSearchTripPreviewMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.grpc.Trip.ConfirmBookingRequest,
      proto.grpc.Trip.ConfirmBookingResponse> getConfirmBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "ConfirmBooking",
      requestType = proto.grpc.Trip.ConfirmBookingRequest.class,
      responseType = proto.grpc.Trip.ConfirmBookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.grpc.Trip.ConfirmBookingRequest,
      proto.grpc.Trip.ConfirmBookingResponse> getConfirmBookingMethod() {
    io.grpc.MethodDescriptor<proto.grpc.Trip.ConfirmBookingRequest, proto.grpc.Trip.ConfirmBookingResponse> getConfirmBookingMethod;
    if ((getConfirmBookingMethod = TripServiceGrpc.getConfirmBookingMethod) == null) {
      synchronized (TripServiceGrpc.class) {
        if ((getConfirmBookingMethod = TripServiceGrpc.getConfirmBookingMethod) == null) {
          TripServiceGrpc.getConfirmBookingMethod = getConfirmBookingMethod =
              io.grpc.MethodDescriptor.<proto.grpc.Trip.ConfirmBookingRequest, proto.grpc.Trip.ConfirmBookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "ConfirmBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.ConfirmBookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.ConfirmBookingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TripServiceMethodDescriptorSupplier("ConfirmBooking"))
              .build();
        }
      }
    }
    return getConfirmBookingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.grpc.Trip.GetIncompletedBookingRequest,
      proto.grpc.Trip.GetIncompletedBookingResponse> getGetIncompletedBookingMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetIncompletedBooking",
      requestType = proto.grpc.Trip.GetIncompletedBookingRequest.class,
      responseType = proto.grpc.Trip.GetIncompletedBookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.grpc.Trip.GetIncompletedBookingRequest,
      proto.grpc.Trip.GetIncompletedBookingResponse> getGetIncompletedBookingMethod() {
    io.grpc.MethodDescriptor<proto.grpc.Trip.GetIncompletedBookingRequest, proto.grpc.Trip.GetIncompletedBookingResponse> getGetIncompletedBookingMethod;
    if ((getGetIncompletedBookingMethod = TripServiceGrpc.getGetIncompletedBookingMethod) == null) {
      synchronized (TripServiceGrpc.class) {
        if ((getGetIncompletedBookingMethod = TripServiceGrpc.getGetIncompletedBookingMethod) == null) {
          TripServiceGrpc.getGetIncompletedBookingMethod = getGetIncompletedBookingMethod =
              io.grpc.MethodDescriptor.<proto.grpc.Trip.GetIncompletedBookingRequest, proto.grpc.Trip.GetIncompletedBookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetIncompletedBooking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.GetIncompletedBookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.GetIncompletedBookingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TripServiceMethodDescriptorSupplier("GetIncompletedBooking"))
              .build();
        }
      }
    }
    return getGetIncompletedBookingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.grpc.Trip.UpdateBookingRequest,
      proto.grpc.Trip.UpdateBookingResponse> getUpdateBookingStatusMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateBookingStatus",
      requestType = proto.grpc.Trip.UpdateBookingRequest.class,
      responseType = proto.grpc.Trip.UpdateBookingResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.grpc.Trip.UpdateBookingRequest,
      proto.grpc.Trip.UpdateBookingResponse> getUpdateBookingStatusMethod() {
    io.grpc.MethodDescriptor<proto.grpc.Trip.UpdateBookingRequest, proto.grpc.Trip.UpdateBookingResponse> getUpdateBookingStatusMethod;
    if ((getUpdateBookingStatusMethod = TripServiceGrpc.getUpdateBookingStatusMethod) == null) {
      synchronized (TripServiceGrpc.class) {
        if ((getUpdateBookingStatusMethod = TripServiceGrpc.getUpdateBookingStatusMethod) == null) {
          TripServiceGrpc.getUpdateBookingStatusMethod = getUpdateBookingStatusMethod =
              io.grpc.MethodDescriptor.<proto.grpc.Trip.UpdateBookingRequest, proto.grpc.Trip.UpdateBookingResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateBookingStatus"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.UpdateBookingRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.UpdateBookingResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TripServiceMethodDescriptorSupplier("UpdateBookingStatus"))
              .build();
        }
      }
    }
    return getUpdateBookingStatusMethod;
  }

  private static volatile io.grpc.MethodDescriptor<proto.grpc.Trip.GetBookingHistoryRequest,
      proto.grpc.Trip.GetBookingHistoryResponse> getGetBookingHistoryMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetBookingHistory",
      requestType = proto.grpc.Trip.GetBookingHistoryRequest.class,
      responseType = proto.grpc.Trip.GetBookingHistoryResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<proto.grpc.Trip.GetBookingHistoryRequest,
      proto.grpc.Trip.GetBookingHistoryResponse> getGetBookingHistoryMethod() {
    io.grpc.MethodDescriptor<proto.grpc.Trip.GetBookingHistoryRequest, proto.grpc.Trip.GetBookingHistoryResponse> getGetBookingHistoryMethod;
    if ((getGetBookingHistoryMethod = TripServiceGrpc.getGetBookingHistoryMethod) == null) {
      synchronized (TripServiceGrpc.class) {
        if ((getGetBookingHistoryMethod = TripServiceGrpc.getGetBookingHistoryMethod) == null) {
          TripServiceGrpc.getGetBookingHistoryMethod = getGetBookingHistoryMethod =
              io.grpc.MethodDescriptor.<proto.grpc.Trip.GetBookingHistoryRequest, proto.grpc.Trip.GetBookingHistoryResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetBookingHistory"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.GetBookingHistoryRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  proto.grpc.Trip.GetBookingHistoryResponse.getDefaultInstance()))
              .setSchemaDescriptor(new TripServiceMethodDescriptorSupplier("GetBookingHistory"))
              .build();
        }
      }
    }
    return getGetBookingHistoryMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TripServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TripServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TripServiceStub>() {
        @java.lang.Override
        public TripServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TripServiceStub(channel, callOptions);
        }
      };
    return TripServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TripServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TripServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TripServiceBlockingStub>() {
        @java.lang.Override
        public TripServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TripServiceBlockingStub(channel, callOptions);
        }
      };
    return TripServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static TripServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<TripServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<TripServiceFutureStub>() {
        @java.lang.Override
        public TripServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new TripServiceFutureStub(channel, callOptions);
        }
      };
    return TripServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public static abstract class TripServiceImplBase implements io.grpc.BindableService {

    /**
     */
    public void searchTripPreview(proto.grpc.Trip.SearchTripPreviewRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.SearchTripPreviewResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getSearchTripPreviewMethod(), responseObserver);
    }

    /**
     */
    public void confirmBooking(proto.grpc.Trip.ConfirmBookingRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.ConfirmBookingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getConfirmBookingMethod(), responseObserver);
    }

    /**
     */
    public void getIncompletedBooking(proto.grpc.Trip.GetIncompletedBookingRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.GetIncompletedBookingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetIncompletedBookingMethod(), responseObserver);
    }

    /**
     */
    public void updateBookingStatus(proto.grpc.Trip.UpdateBookingRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.UpdateBookingResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateBookingStatusMethod(), responseObserver);
    }

    /**
     */
    public void getBookingHistory(proto.grpc.Trip.GetBookingHistoryRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.GetBookingHistoryResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetBookingHistoryMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSearchTripPreviewMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                proto.grpc.Trip.SearchTripPreviewRequest,
                proto.grpc.Trip.SearchTripPreviewResponse>(
                  this, METHODID_SEARCH_TRIP_PREVIEW)))
          .addMethod(
            getConfirmBookingMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                proto.grpc.Trip.ConfirmBookingRequest,
                proto.grpc.Trip.ConfirmBookingResponse>(
                  this, METHODID_CONFIRM_BOOKING)))
          .addMethod(
            getGetIncompletedBookingMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                proto.grpc.Trip.GetIncompletedBookingRequest,
                proto.grpc.Trip.GetIncompletedBookingResponse>(
                  this, METHODID_GET_INCOMPLETED_BOOKING)))
          .addMethod(
            getUpdateBookingStatusMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                proto.grpc.Trip.UpdateBookingRequest,
                proto.grpc.Trip.UpdateBookingResponse>(
                  this, METHODID_UPDATE_BOOKING_STATUS)))
          .addMethod(
            getGetBookingHistoryMethod(),
            io.grpc.stub.ServerCalls.asyncUnaryCall(
              new MethodHandlers<
                proto.grpc.Trip.GetBookingHistoryRequest,
                proto.grpc.Trip.GetBookingHistoryResponse>(
                  this, METHODID_GET_BOOKING_HISTORY)))
          .build();
    }
  }

  /**
   */
  public static final class TripServiceStub extends io.grpc.stub.AbstractAsyncStub<TripServiceStub> {
    private TripServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TripServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TripServiceStub(channel, callOptions);
    }

    /**
     */
    public void searchTripPreview(proto.grpc.Trip.SearchTripPreviewRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.SearchTripPreviewResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getSearchTripPreviewMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void confirmBooking(proto.grpc.Trip.ConfirmBookingRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.ConfirmBookingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getConfirmBookingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getIncompletedBooking(proto.grpc.Trip.GetIncompletedBookingRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.GetIncompletedBookingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetIncompletedBookingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateBookingStatus(proto.grpc.Trip.UpdateBookingRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.UpdateBookingResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateBookingStatusMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getBookingHistory(proto.grpc.Trip.GetBookingHistoryRequest request,
        io.grpc.stub.StreamObserver<proto.grpc.Trip.GetBookingHistoryResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetBookingHistoryMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   */
  public static final class TripServiceBlockingStub extends io.grpc.stub.AbstractBlockingStub<TripServiceBlockingStub> {
    private TripServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TripServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TripServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public proto.grpc.Trip.SearchTripPreviewResponse searchTripPreview(proto.grpc.Trip.SearchTripPreviewRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getSearchTripPreviewMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.grpc.Trip.ConfirmBookingResponse confirmBooking(proto.grpc.Trip.ConfirmBookingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getConfirmBookingMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.grpc.Trip.GetIncompletedBookingResponse getIncompletedBooking(proto.grpc.Trip.GetIncompletedBookingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetIncompletedBookingMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.grpc.Trip.UpdateBookingResponse updateBookingStatus(proto.grpc.Trip.UpdateBookingRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateBookingStatusMethod(), getCallOptions(), request);
    }

    /**
     */
    public proto.grpc.Trip.GetBookingHistoryResponse getBookingHistory(proto.grpc.Trip.GetBookingHistoryRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetBookingHistoryMethod(), getCallOptions(), request);
    }
  }

  /**
   */
  public static final class TripServiceFutureStub extends io.grpc.stub.AbstractFutureStub<TripServiceFutureStub> {
    private TripServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TripServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new TripServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.grpc.Trip.SearchTripPreviewResponse> searchTripPreview(
        proto.grpc.Trip.SearchTripPreviewRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getSearchTripPreviewMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.grpc.Trip.ConfirmBookingResponse> confirmBooking(
        proto.grpc.Trip.ConfirmBookingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getConfirmBookingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.grpc.Trip.GetIncompletedBookingResponse> getIncompletedBooking(
        proto.grpc.Trip.GetIncompletedBookingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetIncompletedBookingMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.grpc.Trip.UpdateBookingResponse> updateBookingStatus(
        proto.grpc.Trip.UpdateBookingRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateBookingStatusMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<proto.grpc.Trip.GetBookingHistoryResponse> getBookingHistory(
        proto.grpc.Trip.GetBookingHistoryRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetBookingHistoryMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEARCH_TRIP_PREVIEW = 0;
  private static final int METHODID_CONFIRM_BOOKING = 1;
  private static final int METHODID_GET_INCOMPLETED_BOOKING = 2;
  private static final int METHODID_UPDATE_BOOKING_STATUS = 3;
  private static final int METHODID_GET_BOOKING_HISTORY = 4;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TripServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TripServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEARCH_TRIP_PREVIEW:
          serviceImpl.searchTripPreview((proto.grpc.Trip.SearchTripPreviewRequest) request,
              (io.grpc.stub.StreamObserver<proto.grpc.Trip.SearchTripPreviewResponse>) responseObserver);
          break;
        case METHODID_CONFIRM_BOOKING:
          serviceImpl.confirmBooking((proto.grpc.Trip.ConfirmBookingRequest) request,
              (io.grpc.stub.StreamObserver<proto.grpc.Trip.ConfirmBookingResponse>) responseObserver);
          break;
        case METHODID_GET_INCOMPLETED_BOOKING:
          serviceImpl.getIncompletedBooking((proto.grpc.Trip.GetIncompletedBookingRequest) request,
              (io.grpc.stub.StreamObserver<proto.grpc.Trip.GetIncompletedBookingResponse>) responseObserver);
          break;
        case METHODID_UPDATE_BOOKING_STATUS:
          serviceImpl.updateBookingStatus((proto.grpc.Trip.UpdateBookingRequest) request,
              (io.grpc.stub.StreamObserver<proto.grpc.Trip.UpdateBookingResponse>) responseObserver);
          break;
        case METHODID_GET_BOOKING_HISTORY:
          serviceImpl.getBookingHistory((proto.grpc.Trip.GetBookingHistoryRequest) request,
              (io.grpc.stub.StreamObserver<proto.grpc.Trip.GetBookingHistoryResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class TripServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TripServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return proto.grpc.Trip.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("com.example.Service.TripService");
    }
  }

  private static final class TripServiceFileDescriptorSupplier
      extends TripServiceBaseDescriptorSupplier {
    TripServiceFileDescriptorSupplier() {}
  }

  private static final class TripServiceMethodDescriptorSupplier
      extends TripServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TripServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (TripServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TripServiceFileDescriptorSupplier())
              .addMethod(getSearchTripPreviewMethod())
              .addMethod(getConfirmBookingMethod())
              .addMethod(getGetIncompletedBookingMethod())
              .addMethod(getUpdateBookingStatusMethod())
              .addMethod(getGetBookingHistoryMethod())
              .build();
        }
      }
    }
    return result;
  }
}
