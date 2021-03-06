//if class A is a subtype of class B, then we should be able to replace B with A without disrupting the behavior of our program.
class Order{

	public Item[] getItems(){ 
		return null;
	}
}

class Item{

	boolean isInStock(){
		return true;
	}
	boolean isPacked(){
		return true;
	}
}

 class OrderStockValidator {

    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if (! item.isInStock()) {
                return false;
            }
        }

        return true;
    }
}

/* class OrderStockAndPackValidator extends OrderStockValidator {

    @Override
    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if ( !item.isInStock() || !item.isPacked() ){
                throw new IllegalStateException(
                     String.format("Order %d is not valid!", order.getId())
                );
            }
        }

        return true;
    }
}
*/
//Однако в данном классе мы нарушили принцип LSP, так как вместо того, 
//чтобы вернуть false, если заказ не прошел валидацию, наш метод бросает 
//исключение IllegalStateException. Клиенты данного кода не рассчитывают 
//на такое: они ожидают возвращения true или false. Это может привести к 
//ошибкам в работе программы.


 class OrderStockAndPackValidator extends OrderStockValidator {

    @Override
    public boolean isValid(Order order) {
        for (Item item : order.getItems()) {
            if ( !item.isInStock() || !item.isPacked() ){
                return false;
            }
        }

        return true;
    }
}
