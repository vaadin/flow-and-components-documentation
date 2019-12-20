SVG =
RASTERIMAGES = installation-steps toolchain

RASTERSRCIMAGES := $(foreach file, $(RASTERIMAGES), $(file).svg)
RASTERIMAGES_HI := $(foreach file, $(RASTERIMAGES), ../images/$(file)-hi.png)
SVGTRGIMAGES := $(foreach file, $(SVG), ../images/$(file).svg)

images: $(RASTERIMAGES_HI) $(SVGTRGIMAGES) FORCE
# Just do low now $(TRGIMAGES_LO)

$(RASTERIMAGES_HI): ../images/%-hi.png: %.svg
	inkscape --export-png $@ --export-dpi=300 --export-area-page $<

$(SVGTRGIMAGES): ../images/%.svg: %.svg FORCE
	inkscape $< --export-text-to-path -l $@

FORCE:
