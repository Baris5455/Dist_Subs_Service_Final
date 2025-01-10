// Generated by the protocol buffer compiler.  DO NOT EDIT!
// NO CHECKED-IN PROTOBUF GENCODE
// source: configuration.proto
// Protobuf Java Version: 4.29.1

package comm.proto.bilgi;

public final class ConfigurationProto {
  private ConfigurationProto() {}
  static {
    com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
      com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
      /* major= */ 4,
      /* minor= */ 29,
      /* patch= */ 1,
      /* suffix= */ "",
      ConfigurationProto.class.getName());
  }
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  /**
   * Protobuf enum {@code bilgi.MethodType}
   */
  public enum MethodType
      implements com.google.protobuf.ProtocolMessageEnum {
    /**
     * <code>START = 0;</code>
     */
    START(0),
    /**
     * <code>STOP = 1;</code>
     */
    STOP(1),
    UNRECOGNIZED(-1),
    ;

    static {
      com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
        com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
        /* major= */ 4,
        /* minor= */ 29,
        /* patch= */ 1,
        /* suffix= */ "",
        MethodType.class.getName());
    }
    /**
     * <code>START = 0;</code>
     */
    public static final int START_VALUE = 0;
    /**
     * <code>STOP = 1;</code>
     */
    public static final int STOP_VALUE = 1;


    public final int getNumber() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalArgumentException(
            "Can't get the number of an unknown enum value.");
      }
      return value;
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     * @deprecated Use {@link #forNumber(int)} instead.
     */
    @java.lang.Deprecated
    public static MethodType valueOf(int value) {
      return forNumber(value);
    }

    /**
     * @param value The numeric wire value of the corresponding enum entry.
     * @return The enum associated with the given numeric wire value.
     */
    public static MethodType forNumber(int value) {
      switch (value) {
        case 0: return START;
        case 1: return STOP;
        default: return null;
      }
    }

    public static com.google.protobuf.Internal.EnumLiteMap<MethodType>
        internalGetValueMap() {
      return internalValueMap;
    }
    private static final com.google.protobuf.Internal.EnumLiteMap<
        MethodType> internalValueMap =
          new com.google.protobuf.Internal.EnumLiteMap<MethodType>() {
            public MethodType findValueByNumber(int number) {
              return MethodType.forNumber(number);
            }
          };

    public final com.google.protobuf.Descriptors.EnumValueDescriptor
        getValueDescriptor() {
      if (this == UNRECOGNIZED) {
        throw new java.lang.IllegalStateException(
            "Can't get the descriptor of an unrecognized enum value.");
      }
      return getDescriptor().getValues().get(ordinal());
    }
    public final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptorForType() {
      return getDescriptor();
    }
    public static final com.google.protobuf.Descriptors.EnumDescriptor
        getDescriptor() {
      return comm.proto.bilgi.ConfigurationProto.getDescriptor().getEnumTypes().get(0);
    }

    private static final MethodType[] VALUES = values();

    public static MethodType valueOf(
        com.google.protobuf.Descriptors.EnumValueDescriptor desc) {
      if (desc.getType() != getDescriptor()) {
        throw new java.lang.IllegalArgumentException(
          "EnumValueDescriptor is not for this type.");
      }
      if (desc.getIndex() == -1) {
        return UNRECOGNIZED;
      }
      return VALUES[desc.getIndex()];
    }

    private final int value;

    private MethodType(int value) {
      this.value = value;
    }

    // @@protoc_insertion_point(enum_scope:bilgi.MethodType)
  }

  public interface ConfigurationOrBuilder extends
      // @@protoc_insertion_point(interface_extends:bilgi.Configuration)
      com.google.protobuf.MessageOrBuilder {

    /**
     * <code>int32 fault_tolerance_level = 1;</code>
     * @return The faultToleranceLevel.
     */
    int getFaultToleranceLevel();

    /**
     * <code>.bilgi.MethodType method_type = 2;</code>
     * @return The enum numeric value on the wire for methodType.
     */
    int getMethodTypeValue();
    /**
     * <code>.bilgi.MethodType method_type = 2;</code>
     * @return The methodType.
     */
    comm.proto.bilgi.ConfigurationProto.MethodType getMethodType();
  }
  /**
   * Protobuf type {@code bilgi.Configuration}
   */
  public static final class Configuration extends
      com.google.protobuf.GeneratedMessage implements
      // @@protoc_insertion_point(message_implements:bilgi.Configuration)
      ConfigurationOrBuilder {
  private static final long serialVersionUID = 0L;
    static {
      com.google.protobuf.RuntimeVersion.validateProtobufGencodeVersion(
        com.google.protobuf.RuntimeVersion.RuntimeDomain.PUBLIC,
        /* major= */ 4,
        /* minor= */ 29,
        /* patch= */ 1,
        /* suffix= */ "",
        Configuration.class.getName());
    }
    // Use Configuration.newBuilder() to construct.
    private Configuration(com.google.protobuf.GeneratedMessage.Builder<?> builder) {
      super(builder);
    }
    private Configuration() {
      methodType_ = 0;
    }

    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return comm.proto.bilgi.ConfigurationProto.internal_static_bilgi_Configuration_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return comm.proto.bilgi.ConfigurationProto.internal_static_bilgi_Configuration_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              comm.proto.bilgi.ConfigurationProto.Configuration.class, comm.proto.bilgi.ConfigurationProto.Configuration.Builder.class);
    }

    public static final int FAULT_TOLERANCE_LEVEL_FIELD_NUMBER = 1;
    private int faultToleranceLevel_ = 0;
    /**
     * <code>int32 fault_tolerance_level = 1;</code>
     * @return The faultToleranceLevel.
     */
    @java.lang.Override
    public int getFaultToleranceLevel() {
      return faultToleranceLevel_;
    }

    public static final int METHOD_TYPE_FIELD_NUMBER = 2;
    private int methodType_ = 0;
    /**
     * <code>.bilgi.MethodType method_type = 2;</code>
     * @return The enum numeric value on the wire for methodType.
     */
    @java.lang.Override public int getMethodTypeValue() {
      return methodType_;
    }
    /**
     * <code>.bilgi.MethodType method_type = 2;</code>
     * @return The methodType.
     */
    @java.lang.Override public comm.proto.bilgi.ConfigurationProto.MethodType getMethodType() {
      comm.proto.bilgi.ConfigurationProto.MethodType result = comm.proto.bilgi.ConfigurationProto.MethodType.forNumber(methodType_);
      return result == null ? comm.proto.bilgi.ConfigurationProto.MethodType.UNRECOGNIZED : result;
    }

    private byte memoizedIsInitialized = -1;
    @java.lang.Override
    public final boolean isInitialized() {
      byte isInitialized = memoizedIsInitialized;
      if (isInitialized == 1) return true;
      if (isInitialized == 0) return false;

      memoizedIsInitialized = 1;
      return true;
    }

    @java.lang.Override
    public void writeTo(com.google.protobuf.CodedOutputStream output)
                        throws java.io.IOException {
      if (faultToleranceLevel_ != 0) {
        output.writeInt32(1, faultToleranceLevel_);
      }
      if (methodType_ != comm.proto.bilgi.ConfigurationProto.MethodType.START.getNumber()) {
        output.writeEnum(2, methodType_);
      }
      getUnknownFields().writeTo(output);
    }

    @java.lang.Override
    public int getSerializedSize() {
      int size = memoizedSize;
      if (size != -1) return size;

      size = 0;
      if (faultToleranceLevel_ != 0) {
        size += com.google.protobuf.CodedOutputStream
          .computeInt32Size(1, faultToleranceLevel_);
      }
      if (methodType_ != comm.proto.bilgi.ConfigurationProto.MethodType.START.getNumber()) {
        size += com.google.protobuf.CodedOutputStream
          .computeEnumSize(2, methodType_);
      }
      size += getUnknownFields().getSerializedSize();
      memoizedSize = size;
      return size;
    }

    @java.lang.Override
    public boolean equals(final java.lang.Object obj) {
      if (obj == this) {
       return true;
      }
      if (!(obj instanceof comm.proto.bilgi.ConfigurationProto.Configuration)) {
        return super.equals(obj);
      }
      comm.proto.bilgi.ConfigurationProto.Configuration other = (comm.proto.bilgi.ConfigurationProto.Configuration) obj;

      if (getFaultToleranceLevel()
          != other.getFaultToleranceLevel()) return false;
      if (methodType_ != other.methodType_) return false;
      if (!getUnknownFields().equals(other.getUnknownFields())) return false;
      return true;
    }

    @java.lang.Override
    public int hashCode() {
      if (memoizedHashCode != 0) {
        return memoizedHashCode;
      }
      int hash = 41;
      hash = (19 * hash) + getDescriptor().hashCode();
      hash = (37 * hash) + FAULT_TOLERANCE_LEVEL_FIELD_NUMBER;
      hash = (53 * hash) + getFaultToleranceLevel();
      hash = (37 * hash) + METHOD_TYPE_FIELD_NUMBER;
      hash = (53 * hash) + methodType_;
      hash = (29 * hash) + getUnknownFields().hashCode();
      memoizedHashCode = hash;
      return hash;
    }

    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        java.nio.ByteBuffer data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        java.nio.ByteBuffer data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        com.google.protobuf.ByteString data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        com.google.protobuf.ByteString data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(byte[] data)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        byte[] data,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws com.google.protobuf.InvalidProtocolBufferException {
      return PARSER.parseFrom(data, extensionRegistry);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static comm.proto.bilgi.ConfigurationProto.Configuration parseDelimitedFrom(java.io.InputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input);
    }

    public static comm.proto.bilgi.ConfigurationProto.Configuration parseDelimitedFrom(
        java.io.InputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        com.google.protobuf.CodedInputStream input)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input);
    }
    public static comm.proto.bilgi.ConfigurationProto.Configuration parseFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      return com.google.protobuf.GeneratedMessage
          .parseWithIOException(PARSER, input, extensionRegistry);
    }

    @java.lang.Override
    public Builder newBuilderForType() { return newBuilder(); }
    public static Builder newBuilder() {
      return DEFAULT_INSTANCE.toBuilder();
    }
    public static Builder newBuilder(comm.proto.bilgi.ConfigurationProto.Configuration prototype) {
      return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
    }
    @java.lang.Override
    public Builder toBuilder() {
      return this == DEFAULT_INSTANCE
          ? new Builder() : new Builder().mergeFrom(this);
    }

    @java.lang.Override
    protected Builder newBuilderForType(
        com.google.protobuf.GeneratedMessage.BuilderParent parent) {
      Builder builder = new Builder(parent);
      return builder;
    }
    /**
     * Protobuf type {@code bilgi.Configuration}
     */
    public static final class Builder extends
        com.google.protobuf.GeneratedMessage.Builder<Builder> implements
        // @@protoc_insertion_point(builder_implements:bilgi.Configuration)
        comm.proto.bilgi.ConfigurationProto.ConfigurationOrBuilder {
      public static final com.google.protobuf.Descriptors.Descriptor
          getDescriptor() {
        return comm.proto.bilgi.ConfigurationProto.internal_static_bilgi_Configuration_descriptor;
      }

      @java.lang.Override
      protected com.google.protobuf.GeneratedMessage.FieldAccessorTable
          internalGetFieldAccessorTable() {
        return comm.proto.bilgi.ConfigurationProto.internal_static_bilgi_Configuration_fieldAccessorTable
            .ensureFieldAccessorsInitialized(
                comm.proto.bilgi.ConfigurationProto.Configuration.class, comm.proto.bilgi.ConfigurationProto.Configuration.Builder.class);
      }

      // Construct using comm.proto.bilgi.ConfigurationProto.Configuration.newBuilder()
      private Builder() {

      }

      private Builder(
          com.google.protobuf.GeneratedMessage.BuilderParent parent) {
        super(parent);

      }
      @java.lang.Override
      public Builder clear() {
        super.clear();
        bitField0_ = 0;
        faultToleranceLevel_ = 0;
        methodType_ = 0;
        return this;
      }

      @java.lang.Override
      public com.google.protobuf.Descriptors.Descriptor
          getDescriptorForType() {
        return comm.proto.bilgi.ConfigurationProto.internal_static_bilgi_Configuration_descriptor;
      }

      @java.lang.Override
      public comm.proto.bilgi.ConfigurationProto.Configuration getDefaultInstanceForType() {
        return comm.proto.bilgi.ConfigurationProto.Configuration.getDefaultInstance();
      }

      @java.lang.Override
      public comm.proto.bilgi.ConfigurationProto.Configuration build() {
        comm.proto.bilgi.ConfigurationProto.Configuration result = buildPartial();
        if (!result.isInitialized()) {
          throw newUninitializedMessageException(result);
        }
        return result;
      }

      @java.lang.Override
      public comm.proto.bilgi.ConfigurationProto.Configuration buildPartial() {
        comm.proto.bilgi.ConfigurationProto.Configuration result = new comm.proto.bilgi.ConfigurationProto.Configuration(this);
        if (bitField0_ != 0) { buildPartial0(result); }
        onBuilt();
        return result;
      }

      private void buildPartial0(comm.proto.bilgi.ConfigurationProto.Configuration result) {
        int from_bitField0_ = bitField0_;
        if (((from_bitField0_ & 0x00000001) != 0)) {
          result.faultToleranceLevel_ = faultToleranceLevel_;
        }
        if (((from_bitField0_ & 0x00000002) != 0)) {
          result.methodType_ = methodType_;
        }
      }

      @java.lang.Override
      public Builder mergeFrom(com.google.protobuf.Message other) {
        if (other instanceof comm.proto.bilgi.ConfigurationProto.Configuration) {
          return mergeFrom((comm.proto.bilgi.ConfigurationProto.Configuration)other);
        } else {
          super.mergeFrom(other);
          return this;
        }
      }

      public Builder mergeFrom(comm.proto.bilgi.ConfigurationProto.Configuration other) {
        if (other == comm.proto.bilgi.ConfigurationProto.Configuration.getDefaultInstance()) return this;
        if (other.getFaultToleranceLevel() != 0) {
          setFaultToleranceLevel(other.getFaultToleranceLevel());
        }
        if (other.methodType_ != 0) {
          setMethodTypeValue(other.getMethodTypeValue());
        }
        this.mergeUnknownFields(other.getUnknownFields());
        onChanged();
        return this;
      }

      @java.lang.Override
      public final boolean isInitialized() {
        return true;
      }

      @java.lang.Override
      public Builder mergeFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws java.io.IOException {
        if (extensionRegistry == null) {
          throw new java.lang.NullPointerException();
        }
        try {
          boolean done = false;
          while (!done) {
            int tag = input.readTag();
            switch (tag) {
              case 0:
                done = true;
                break;
              case 8: {
                faultToleranceLevel_ = input.readInt32();
                bitField0_ |= 0x00000001;
                break;
              } // case 8
              case 16: {
                methodType_ = input.readEnum();
                bitField0_ |= 0x00000002;
                break;
              } // case 16
              default: {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
            } // switch (tag)
          } // while (!done)
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.unwrapIOException();
        } finally {
          onChanged();
        } // finally
        return this;
      }
      private int bitField0_;

      private int faultToleranceLevel_ ;
      /**
       * <code>int32 fault_tolerance_level = 1;</code>
       * @return The faultToleranceLevel.
       */
      @java.lang.Override
      public int getFaultToleranceLevel() {
        return faultToleranceLevel_;
      }
      /**
       * <code>int32 fault_tolerance_level = 1;</code>
       * @param value The faultToleranceLevel to set.
       * @return This builder for chaining.
       */
      public Builder setFaultToleranceLevel(int value) {

        faultToleranceLevel_ = value;
        bitField0_ |= 0x00000001;
        onChanged();
        return this;
      }
      /**
       * <code>int32 fault_tolerance_level = 1;</code>
       * @return This builder for chaining.
       */
      public Builder clearFaultToleranceLevel() {
        bitField0_ = (bitField0_ & ~0x00000001);
        faultToleranceLevel_ = 0;
        onChanged();
        return this;
      }

      private int methodType_ = 0;
      /**
       * <code>.bilgi.MethodType method_type = 2;</code>
       * @return The enum numeric value on the wire for methodType.
       */
      @java.lang.Override public int getMethodTypeValue() {
        return methodType_;
      }
      /**
       * <code>.bilgi.MethodType method_type = 2;</code>
       * @param value The enum numeric value on the wire for methodType to set.
       * @return This builder for chaining.
       */
      public Builder setMethodTypeValue(int value) {
        methodType_ = value;
        bitField0_ |= 0x00000002;
        onChanged();
        return this;
      }
      /**
       * <code>.bilgi.MethodType method_type = 2;</code>
       * @return The methodType.
       */
      @java.lang.Override
      public comm.proto.bilgi.ConfigurationProto.MethodType getMethodType() {
        comm.proto.bilgi.ConfigurationProto.MethodType result = comm.proto.bilgi.ConfigurationProto.MethodType.forNumber(methodType_);
        return result == null ? comm.proto.bilgi.ConfigurationProto.MethodType.UNRECOGNIZED : result;
      }
      /**
       * <code>.bilgi.MethodType method_type = 2;</code>
       * @param value The methodType to set.
       * @return This builder for chaining.
       */
      public Builder setMethodType(comm.proto.bilgi.ConfigurationProto.MethodType value) {
        if (value == null) {
          throw new NullPointerException();
        }
        bitField0_ |= 0x00000002;
        methodType_ = value.getNumber();
        onChanged();
        return this;
      }
      /**
       * <code>.bilgi.MethodType method_type = 2;</code>
       * @return This builder for chaining.
       */
      public Builder clearMethodType() {
        bitField0_ = (bitField0_ & ~0x00000002);
        methodType_ = 0;
        onChanged();
        return this;
      }

      // @@protoc_insertion_point(builder_scope:bilgi.Configuration)
    }

    // @@protoc_insertion_point(class_scope:bilgi.Configuration)
    private static final comm.proto.bilgi.ConfigurationProto.Configuration DEFAULT_INSTANCE;
    static {
      DEFAULT_INSTANCE = new comm.proto.bilgi.ConfigurationProto.Configuration();
    }

    public static comm.proto.bilgi.ConfigurationProto.Configuration getDefaultInstance() {
      return DEFAULT_INSTANCE;
    }

    private static final com.google.protobuf.Parser<Configuration>
        PARSER = new com.google.protobuf.AbstractParser<Configuration>() {
      @java.lang.Override
      public Configuration parsePartialFrom(
          com.google.protobuf.CodedInputStream input,
          com.google.protobuf.ExtensionRegistryLite extensionRegistry)
          throws com.google.protobuf.InvalidProtocolBufferException {
        Builder builder = newBuilder();
        try {
          builder.mergeFrom(input, extensionRegistry);
        } catch (com.google.protobuf.InvalidProtocolBufferException e) {
          throw e.setUnfinishedMessage(builder.buildPartial());
        } catch (com.google.protobuf.UninitializedMessageException e) {
          throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
        } catch (java.io.IOException e) {
          throw new com.google.protobuf.InvalidProtocolBufferException(e)
              .setUnfinishedMessage(builder.buildPartial());
        }
        return builder.buildPartial();
      }
    };

    public static com.google.protobuf.Parser<Configuration> parser() {
      return PARSER;
    }

    @java.lang.Override
    public com.google.protobuf.Parser<Configuration> getParserForType() {
      return PARSER;
    }

    @java.lang.Override
    public comm.proto.bilgi.ConfigurationProto.Configuration getDefaultInstanceForType() {
      return DEFAULT_INSTANCE;
    }

  }

  private static final com.google.protobuf.Descriptors.Descriptor
    internal_static_bilgi_Configuration_descriptor;
  private static final 
    com.google.protobuf.GeneratedMessage.FieldAccessorTable
      internal_static_bilgi_Configuration_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    java.lang.String[] descriptorData = {
      "\n\023configuration.proto\022\005bilgi\"V\n\rConfigur" +
      "ation\022\035\n\025fault_tolerance_level\030\001 \001(\005\022&\n\013" +
      "method_type\030\002 \001(\0162\021.bilgi.MethodType*!\n\n" +
      "MethodType\022\t\n\005START\020\000\022\010\n\004STOP\020\001B&\n\020comm." +
      "proto.bilgiB\022ConfigurationProtob\006proto3"
    };
    descriptor = com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
        });
    internal_static_bilgi_Configuration_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_bilgi_Configuration_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessage.FieldAccessorTable(
        internal_static_bilgi_Configuration_descriptor,
        new java.lang.String[] { "FaultToleranceLevel", "MethodType", });
    descriptor.resolveAllFeaturesImmutable();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
