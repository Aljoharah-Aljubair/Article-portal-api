export class Article { 
    constructor(
      public title: string, 
      public body: string,
      public authorUsername:string,
      public id:number,
      public numberOfLikes:number,
      public numberOfDislikes:number
    ) {}
  }
  