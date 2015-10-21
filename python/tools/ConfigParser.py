#! /usr/bin/env python
#coding=utf-8
import configparser

config = configparser.ConfigParser()
config.read("example.ini")

sections = config.sections()
print(sections)

print("info" in config)

print(config["db"])
print(config["db"]["db_port"])
