export interface Product{
    id:string
    name:string,
    price: number,
    description:string,
    category:Category,
    image:string
}

export enum Category {
    ELECTRICAL = "electrical",
    JEWELS = "jewels",
    LADY = "lady",
    MAN = "man",
    GUNS= "guns"
}