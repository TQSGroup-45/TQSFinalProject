export interface Product {
    id: number,
    price:number,
    name:string,
    class:string,
    color:string,
    gender:string,
    src:string
}
export const prods:Product[]=[
    {id: 0,price:39.99,name:"Brown pants",color:"Brown",class:"Pants",gender:"Male",src:"1"},
    {id: 1,price:29.99,name:"Blue T-shirt",color:"Blue",class:"T-shirt",gender:"Male",src:"2"},
    {id: 2,price:19.99,name:"Yellow T-shirt",color:"Yellow",class:"T-shirt",gender:"Undefined",src:"3"},
    {id: 3,price:9.99,name:"Pringle Socks",color:"Undefined",class:"Socks",gender:"Undefined",src:"4"},
    {id: 4,price:39.99,name:"Black Dress",color:"Black",class:"Dress",gender:"Female",src:"5"},
    {id: 5,price:59.99,name:"Green Dress",color:"Green",class:"Dress",gender:"Female",src:"6"},
    {id: 6,price:39.99,name:"Black Sneakers",color:"Black",class:"Sneakers",gender:"Undefined",src:"7"},
    {id: 7,price:59.99,name:"White Sneakers",color:"White",class:"Sneakers",gender:"Undefined",src:"8"},
  ];
