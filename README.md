# Description

I went for a microservice based architecture so that the system could be scalable as the task is based on a e-commerce site. I decided not to implement docker/containers because I felt it may go beyond the scope of the exercise, but thats fundamentally why I chose ms based architecture in the first place, for extensibility and flexibility. That does not only apply to the context of additional technology, but maintaing the service functionality overall from a programming perspective. 

The way I designed this API is that the cart service holds the data it needs to match its products using a composite key, it is arguably the more complex aspect of the system due to the nature of managing its products. The products alone are fairly simple.

Things I took into consideration is the length a user enters for the price of a product (rounding it to two decimal places), whether or not they entered any labels in atall/not the correct ones, etc.
The cart requires more attention because it is not only about updating a cart with a new product, but updating a cart with a different quantity of the same product, maintaining integrity of the cart as you update it. 

The downside of the way I've built this software system is that the cart calls on the product service a fair amount to perform it's duty. It may of been better to build a monolithic style system in this instance so that the cart has access to the same repository interface as the product.
Nonetheless, if there were more functionality added to product service, it could balance out - for example seeing how many people have a particular product in their cart like on ebay, would require the product service to call on the carts services. The relationship between cart and product will evolved depending on the desired functionality.
