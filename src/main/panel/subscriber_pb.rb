# frozen_string_literal: true
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: subscriber.proto

require 'google/protobuf'


descriptor_data = "\n\x10subscriber.proto\x12\x04info\"A\n\nSubscriber\x12\x15\n\rsubscriber_id\x18\x01 \x01(\x03\x12\x0c\n\x04name\x18\x02 \x01(\t\x12\x0e\n\x06status\x18\x03 \x01(\tB#\n\x10\x63omm.struct.infoB\x0fSubscriberProtob\x06proto3"

pool = Google::Protobuf::DescriptorPool.generated_pool
pool.add_serialized_file(descriptor_data)

module Info
  Subscriber = ::Google::Protobuf::DescriptorPool.generated_pool.lookup("info.Subscriber").msgclass
end
