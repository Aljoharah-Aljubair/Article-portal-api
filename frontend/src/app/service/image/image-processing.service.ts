import { Injectable } from '@angular/core';
import { DomSanitizer } from '@angular/platform-browser';
import { Article } from 'src/app/classes/article';
import { FileHandel } from 'src/app/classes/file-handle';

@Injectable({
  providedIn: 'root'
})
export class ImageProcessingService {

  constructor( private sanitizer :DomSanitizer) { }

  public createImages(article:Article){
   const articleImages:any[]= article.articleImages;

   const articleImagesToFileHandle:FileHandel[]=[];

   for(let i =0; i<articleImages.length;i++){
    const imageFileData=articleImages[i];
    const imageBlob = this.dataURItoBlob(imageFileData.picByte,imageFileData.type);

    const imageFile =  new File([imageBlob],imageFileData.name,{type:imageFileData.type});

    const finalFileHandel:FileHandel={
      file:imageFile,
      url:this.sanitizer.bypassSecurityTrustUrl(window.URL.createObjectURL(imageFile))
    };

    articleImagesToFileHandle.push(finalFileHandel);
   }

   article.articleImages= articleImagesToFileHandle;
   return article;
  }


  public dataURItoBlob(picBytes: string,imageType: any){
   const byteString = window.atob(picBytes);
    const arrayBuffer = new ArrayBuffer(byteString.length);
    const int8Array=new Uint8Array(arrayBuffer);

    for(let i = 0 ; i < byteString.length; i++){
      int8Array[i]=byteString.charCodeAt(i);
      
    }

    const blob = new Blob([int8Array],{type:imageType});
    return blob
  }
}
