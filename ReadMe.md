# Read Me

## Purpose

This project demonstrates delivery of non-json resources in
[crnk.io](https://crnk.io).

The problem we had to solve was like this: a REST service
with a JSON:API-Interface that manages and returns documents.
The service was required to return either the document
resource as JSON object or the raw document as PDF or
other document formats.

We wanted to use the http `accept` header to choose between
JSON:API and raw document data and we decided to use
[crnk.io](https://crnk.io).

The [crnk.io](https://crnk.io) documentation does not
describe exactly how to do this and so I wrote this
little example program to demonstrate how the problem
can be solved with [crnk.io](https://crnk.io).

## Solution

The secret sauce is in the `ext` package. [crnk.io](https://crnk.io)
detects modules on startup and initializes them.
The `BinaryResponseModule` registers an instance
of `BinaryRequestProcessor` which is derived from the
`JsonApiRequestProcessor` and overrides the
`accepts` method to check the http `accept` header but you
could also check for resource names at this point.

The `toHttpResponse` method retrieves the document from
the resource, sets the content type and returns the data
stream.